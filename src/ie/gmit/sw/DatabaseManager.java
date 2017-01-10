package ie.gmit.sw;

import com.db4o.*;
import com.db4o.config.*;
import com.db4o.ta.*;
import xtea_db4o.*;
import java.io.*;

/**
 * Created by Ross Byrne on 10/01/17.
 * Class to manage the DB4O database.
 */
public class DatabaseManager {

    private ObjectContainer db = null;
    private File dataFile = null;

    public DatabaseManager() {

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().add(new TransparentActivationSupport()); //Real lazy. Saves all the config commented out below
        config.common().add(new TransparentPersistenceSupport()); //Lazier still. Saves all the config commented out below
        config.common().updateDepth(7); //Propagate updates

        //Use the XTea lib for encryption. The basic Db4O container only has a Caesar cypher... Dicas quod non est ita!
        config.file().storage(new XTeaEncryptionStorage("password", XTEA.ITERATIONS64));

		// check if datafile exist, otherwise create it
        dataFile = new File("metricData.data");

        //Open a local database. Use Db4o.openServer(config, server, port) for full client / server
        db = Db4oEmbedded.openFile(config, "metricData.data");

    }

    /**
     * Adds the MetricData object to the database.
     *
     * @param data
     * The metrics data, in a 2 Dimensional object array format.
     */
    public void addMetricsToDatabase(MetricData data){

        //An ObjectSet is a specialised List for storing results
        ObjectSet<MetricData> metrics = db.query(MetricData.class);

        // delete existing metrics
        for(MetricData m : metrics){

            // delete existing objects
            db.delete(m);

        } // for

        //Adds new metrics object to the database
        db.store(data);

        //Commits the transaction
        db.commit();

    } // addMetricsToDatabase()

    /**
     * Gets the MetricData object from the database. Returns null if not there.
     *
     * @return
     * Returns the MetricsData from the DB, returns null if it's not there.
     */
    public MetricData getMetricsFromDB(){

        //An ObjectSet is a specialised List for storing results
        ObjectSet<MetricData> metrics = db.query(MetricData.class);

        if(metrics.size() > 0) {

            return metrics.next();
        } else {

            return null;
        } // if

    } // getMetricsFromDB()

 } // class
