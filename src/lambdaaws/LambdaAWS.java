package lambdaaws;
       
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hugo de la Mora
 */
public class LambdaAWS implements RequestHandler<RequestClass, String> {

    @Override
    public String handleRequest(RequestClass i, Context cntxt) {
        cntxt.getLogger().log(("Entrada: "+i.sensorID+" "+ i.temperatura + "\n"));
        
        AmazonDynamoDB db = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
        String tableName = "BitacoraLectura";
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("sensorID", new AttributeValue(i.sensorID));
        item.put("temperatura", new AttributeValue(i.temperatura));
        db.putItem(tableName, item);
        return "Agregado: "+i.sensorID+" "+i.temperatura;
    
    
    }
}
