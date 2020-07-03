package util;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Gurmandeep Kaur
 * @version 1.0
 * @info used for common utility methods
 *
 */
public class SeenUtility {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(SeenUtility.class);

	SeenUtility() {

	}

	/**
	 * @param obj
	 * @return the json string of the object.
	 */
	public static String toJsonString(Object obj) {

		String jsonString = null;
		try {
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			LOGGER.info("Exception in toJsonString", e);
		}

		return jsonString;
	}

	/**
	 * Get Headers Without Token
	 * 
	 * @return HttpHeaders
	 */
	public static HttpHeaders getHeadersWithoutToken() {
		// Create the Rest Template and headers
		HttpHeaders headers = new HttpHeaders();

		// Set Media Type Json in header
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		return headers;
	}

	/**
	 * @param timePeriod
	 * @return LocalDateTime object
	 * @use This method calculates the time based on specific cases.
	 */
	public static LocalDateTime calculateTime(String timePeriod) {
		LocalDateTime time = LocalDateTime.now();
		switch (timePeriod == null ? Constants.EMPTY_STRING : timePeriod) {
		case "2h":
			time = time.minusHours(2);
			break;
		case "24h":
			time = time.minusDays(1);
			break;
		case "today":
			time = time.minusHours(time.getHour());
			time = time.minusMinutes(time.getMinute());
			time = time.minusSeconds(time.getSecond());
			break;
		case "month":
			time = time.minusHours(time.getHour());
			time = time.minusMinutes(time.getMinute());
			time = time.minusSeconds(time.getSecond());
			time = time.minusDays(time.getDayOfMonth() - 1L);
			break;
		case "7D":
			time = time.minusHours(time.getHour());
			time = time.minusMinutes(time.getMinute());
			time = time.minusSeconds(time.getSecond());
			time = time.minusDays(7);
			break;
		default:
			break;
		}
		return time;
	}
	
	
	public static RestTemplate restTemplateObj() {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext;
		try {
			sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
					.build();
			
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
			return new RestTemplate(requestFactory);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			LOGGER.error("Error in SeenUtitity restTemplateObj > " + e);
		}
		return new RestTemplate();
	}

}
