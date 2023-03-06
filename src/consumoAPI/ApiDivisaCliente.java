package consumoAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import com.google.gson.Gson;

public class ApiDivisaCliente {

	private static final String API_URL = "https://openexchangerates.org/api/latest.json?app_id=ee31721ec15d46ab91f443219bb0dec9";
	
	public double apiGetData(String divisa) {
		try {
			URL url = new URL(API_URL);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content_Type", "aplication/json");
			connection.connect();
			
			int responseCode = connection.getResponseCode();
			
			if(responseCode != 200) {
				throw new RuntimeException("Ocurrio un error: " + responseCode);
			} else {
				BufferedReader scanner = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				
				while((inputLine = scanner.readLine()) != null) {
					response.append(inputLine);
				}
				
				scanner.close();
				
				
				
				try {
//					JSONArray coleccionDivisas = new JSONArray(response.toString());
//					JSONObject objetoDivisas = coleccionDivisas.getJSONObject(0);	
//					//JSONObject objetoDivisas = response.getJSONObject(0);	
					Gson gson = new Gson();
					ExchangeRatesResponse exchangeRatesResponse = gson.fromJson(response.toString(), ExchangeRatesResponse.class);					
					
									
					double currencyValue = exchangeRatesResponse.rates.get(divisa);
					return currencyValue;
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
