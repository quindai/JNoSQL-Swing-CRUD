package br.ic.ufal.bd2.dao;

//******************Mongo***********************
import org.jnosql.diana.api.document.*;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;
import java.util.List;
import java.util.Optional;
//**********************************************

//******************Redis***********************
import org.jnosql.diana.api.Value;
import org.jnosql.diana.api.key.BucketManager;
import org.jnosql.diana.api.key.BucketManagerFactory;
import org.jnosql.diana.api.key.KeyValueConfiguration;
import org.jnosql.diana.hazelcast.key.HazelCastKeyValueConfiguration;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
//**********************************************


//*****************CouchBase********************
import java.util.UUID;
import org.jnosql.diana.couchbase.document.CouchbaseDocumentConfiguration;
//**********************************************


//*****************Cassandra********************
/*import com.datastax.driver.core.ConsistencyLevel;
import org.jnosql.diana.api.column.Column;
import org.jnosql.diana.api.column.ColumnCondition;
import org.jnosql.diana.api.column.ColumnEntity;
import org.jnosql.diana.api.column.ColumnQuery;
import org.jnosql.diana.cassandra.column.CassandraColumnFamilyManager;
import org.jnosql.diana.cassandra.column.CassandraConfiguration;
import org.jnosql.diana.cassandra.column.CassandraDocumentEntityManagerFactory;
import org.jnosql.diana.cassandra.column.CassandraPrepareStatment;
*/
import java.util.Arrays;
//***********************************************
public class ConnFactory {

	private static ConnFactory instance = null;
	public static final String DATABASE = "bd2ic";
    public static final String DOCUMENT_COLLECTION = "person";

    public static ConnFactory getInstance() {
        if(instance == null) {
           instance = new ConnFactory();
        }
        return instance;
     }
    
    public static void getMongo()  {
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();
        try(DocumentCollectionManagerFactory collectionFactory = configuration.get();) {
            DocumentCollectionManager collectionManager = collectionFactory.get(DATABASE);

            DocumentEntity entity = DocumentEntity.of(DOCUMENT_COLLECTION);
            entity.add(Document.of("name", "Daniel Soro"));
            entity.add(Document.of("age", 26));

            DocumentEntity entitySaved = collectionManager.save(entity);
            Optional<Document> id = entitySaved.find("_id");

            DocumentQuery query = DocumentQuery.of(DOCUMENT_COLLECTION);
            query.and(DocumentCondition.eq(id.get()));
            List<DocumentEntity> documentsFound = collectionManager.find(query);
        }
    }
    
    public static void getRedis() {
    	KeyValueConfiguration<?> configuration = new HazelCastKeyValueConfiguration();
        try (BucketManagerFactory<?> managerFactory = configuration.get()) {
            BucketManager bucket = managerFactory.getBucketManager("bucket");
            List<String> list = managerFactory.getList("bucketList", String.class);
            Set<String> set = managerFactory.getSet("bucketSet", String.class);
            Map<String, Integer> map = managerFactory.getMap("bucketList", String.class, Integer.class);
            Queue<String> queue = managerFactory.getQueue("queueList", String.class);
            bucket.put("key", "value");
            Optional<Value> value = bucket.get("key");
        }
    }
    
    public static void getCouchBase(){
    	 String idValue = UUID.randomUUID().toString();
         DocumentConfiguration configuration = new CouchbaseDocumentConfiguration();
         try(DocumentCollectionManagerFactory collectionFactory = configuration.get();) {
             DocumentCollectionManager collectionManager = collectionFactory.get(DATABASE);

             DocumentEntity entity = DocumentEntity.of(DOCUMENT_COLLECTION);
             entity.add(Document.of("name", "Daniel Soro"));
             entity.add(Document.of("age", 26));
             entity.add(Document.of("_id", idValue));
             DocumentEntity entitySaved = collectionManager.save(entity);
             Optional<Document> id = entitySaved.find("_id");

             DocumentQuery query = DocumentQuery.of(DOCUMENT_COLLECTION);
             query.and(DocumentCondition.eq(id.get()));
             List<DocumentEntity> documentsFound = collectionManager.find(query);
         }
    }
    
    /*
    public static void getCassandra(){
    	public static final String KEY_SPACE = "newKeySpace";
        public static final String COLUMN_FAMILY = "newColumnFamily";

        public static void main(String[] args) {

            ColumnConfiguration condition = new CassandraConfiguration();

            try(ColumnFamilyManagerFactory managerFactory = condition.get()) {
                ColumnFamilyManager columnEntityManager = managerFactory.get(KEY_SPACE);
                ColumnEntity entity = ColumnEntity.of(COLUMN_FAMILY);
                Column id = Column.of("id", 10L);
                entity.add(id);
                entity.add(Column.of("version", 0.001));
                entity.add(Column.of("name", "Diana"));
                entity.add(Column.of("options", Arrays.asList(1, 2, 3)));

                columnEntityManager.save(entity);

                ColumnQuery query = ColumnQuery.of(COLUMN_FAMILY);
                query.and(ColumnCondition.eq(id));
                Optional<ColumnEntity> result = columnEntityManager.singleResult(query);
                System.out.println(result);

            }
        }*/
}
