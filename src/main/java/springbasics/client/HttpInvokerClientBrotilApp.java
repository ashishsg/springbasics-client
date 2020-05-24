package springbasics.client;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.nixxcode.jvmbrotli.common.BrotliLoader;

import asg.springbasics.service.DemoService;

@Configuration
@SpringBootApplication
public class HttpInvokerClientBrotilApp {

	@Bean
	public HttpInvokerProxyFactoryBean invoker() throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, CertificateException, IOException {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://35.154.131.43:8081/brotlidemo");
		invoker.setServiceInterface(DemoService.class);

//		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
//				new File("E:\\workspace\\Spring\\springbasics-client\\src\\main\\resources\\keystore\\ashishdemo.jks"),
//				"ashish".toCharArray()).build();
////				      .loadTrustMaterial("/resources/keystore/ashish.demo", "ashish")
////				      .build();
//		SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);//, NoopHostnameVerifier.INSTANCE
//		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
////				    HttpComponentsClientHttpRequestFactory factory = 
////				      new HttpComponentsClientHttpRequestFactory(httpClient);
//		HttpComponentsHttpInvokerRequestExecutor requestExecutor = new HttpComponentsHttpInvokerRequestExecutor(
//				httpClient);
				
				
		invoker.setHttpInvokerRequestExecutor(new HttpInvokerBrotliExecutor());
		return invoker;
	}

	public static void main(String[] args) {
		System.out.println("Brotli available: " + BrotliLoader.isBrotliAvailable());
		DemoService service = SpringApplication.run(HttpInvokerClientBrotilApp.class, args).getBean(DemoService.class);
		System.out.println(service.getDemoMessage().size());
		System.out.println(service.getDemoMessage().size());
		System.out.println(service.getDemoMessage().size());
		System.out.println(service.getDemoMessage().size());
		System.out.println(service.getDemoMessage().size());
		System.out.println("Done");
	}

}
