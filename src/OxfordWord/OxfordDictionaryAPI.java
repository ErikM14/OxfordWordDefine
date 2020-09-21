package OxfordWord;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author erikm
 */
public class OxfordDictionaryAPI {
    
    protected String searchedWord(String word)
    {
        String lcWord = word.toLowerCase();
        
        return "https://od-api.oxforddictionaries.com/api/v2/entries/en/" + lcWord;
    }
    
    protected String retrieveInfo(String word){
        String app_id = "dcd6b51e";
        String app_key = "bf445b4ea522260f209d4aa22fb10607";
        
        try{
            URL url = new URL(word);
            
            HttpsURLConnection urlConnect = (HttpsURLConnection) url.openConnection();
            
            urlConnect.setRequestProperty("Accept","application/json");
            urlConnect.setRequestProperty("app_id",app_id);
            urlConnect.setRequestProperty("app_key",app_key);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            
            while((line=reader.readLine())!= null){
                
                stringBuilder.append(line + "\n");
            }
            
            JSONObject json = new JSONObject(stringBuilder.toString());
            JSONArray array = json.getJSONArray("results");
            JSONObject json1 = array.getJSONObject(0);
            JSONArray array2 = json1.getJSONArray("lexicalEntries");
            JSONObject json2 = array2.getJSONObject(0);
            JSONArray array3 = json2.getJSONArray("entries");
            JSONObject json3 = array3.getJSONObject(0);
            JSONArray array4 = json3.getJSONArray("senses");
            JSONObject json4 = array4.getJSONObject(0);
            JSONArray array5 = json4.getJSONArray("definitions");
            String w1 = "";
            try{
            JSONArray array6 = json4.getJSONArray("examples");
            JSONObject json5 = array6.getJSONObject(0);
            w1 = json5.getString("text");
            }
            catch(Exception e){
                w1 = "No example provided";
            }
            JSONArray array7 = json4.getJSONArray("synonyms");
            String w2 = "";
            for(int c=0;c<array7.length();c++){ // Array will get all the synonyms and seperate each with a comma
                JSONObject temp = array7.getJSONObject(c);
                if(c==array7.length()-1)
                    w2+=temp.getString("text");
                else
                    w2 += temp.getString("text") + ", ";
            }
            
            return "Definition: " + array5.getString(0) + "\n\nExample: " + w1 + "\n\nSynonyms: " + w2;
            
        }
        catch(Exception e){
            return "Something went wrong, try checking your spelling of the word.";
        }
    }
}
