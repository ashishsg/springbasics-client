package springbasics.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.zip.GZIPOutputStream;

import org.springframework.remoting.httpinvoker.HttpInvokerClientConfiguration;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor;

public class HttpInvokerGzipExecutor extends SimpleHttpInvokerRequestExecutor {
	
	protected void writeRequestBody(
			HttpInvokerClientConfiguration config, HttpURLConnection con, ByteArrayOutputStream baos)
			throws IOException {
		byte[] byteArray = baos.toByteArray();
		con.setRequestProperty("Accept-Encoding", "gzip");
		con.setRequestProperty("Content-Encoding", "gzip");
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		//Encoder.Parameters params = new Encoder.Parameters().setQuality(5);
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
		gzipOutputStream.write(byteArray);
		gzipOutputStream.finish();
		gzipOutputStream.flush();
		gzipOutputStream.close();
		con.getOutputStream().write(byteArrayOutputStream.toByteArray());
	}
	
//	protected InputStream decorateInputStream(InputStream is) throws IOException {
//		return new BrotliInputStream(is);
//	}

}
