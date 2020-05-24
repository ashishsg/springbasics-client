package springbasics.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.springframework.remoting.httpinvoker.HttpInvokerClientConfiguration;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor;

import com.nixxcode.jvmbrotli.dec.BrotliInputStream;
import com.nixxcode.jvmbrotli.enc.BrotliOutputStream;

public class HttpInvokerBrotliExecutor extends SimpleHttpInvokerRequestExecutor {
	
	protected void writeRequestBody(
			HttpInvokerClientConfiguration config, HttpURLConnection con, ByteArrayOutputStream baos)
			throws IOException {
		byte[] byteArray = baos.toByteArray();
		con.setRequestProperty("Accept-Encoding", "br");
		con.setRequestProperty("Content-Encoding", "br");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		//Encoder.Parameters params = new Encoder.Parameters().setQuality(5);
		BrotliOutputStream brotliOutputStream = new BrotliOutputStream(byteArrayOutputStream);
		brotliOutputStream.write(byteArray);
		brotliOutputStream.flush();
		brotliOutputStream.close();
		con.getOutputStream().write(byteArrayOutputStream.toByteArray());
	}
	
	protected InputStream decorateInputStream(InputStream is) throws IOException {
		return new BrotliInputStream(is);
	}

}
