package consumoAPI;

import java.util.Map;

public class ExchangeRatesResponse {
	public String disclaimer;
	public String license;
	public long timestamp;
	public String base;
	public Map<String, Double> rates;
}
