package br.com.apideteste.projeto;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerImagem {
	
	public static void main(String[] args) {
		salvarImagem();
	}
	
	
	private static void salvarImagem() {
		int contador=0;
		String url = "urlDoSite";
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			Elements image = doc.getElementsByTag("img");
			for(Element src:image) {
				contador++;
				System.out.println("Caminho da imagem "+contador+": "+src.attr("abs:src"));
				teste(contador,src.attr("abs:src"));
			}
		}catch(IOException ex) {
			ex.printStackTrace();
			
		}
		
	}
	
	public static void teste(int contador, String caminho) {
		try {
        final int BUFFER_SIZE = 1024 * 5024;		
		String nomeArquivo = "LocalOndeSalvaraImagens"+contador+".jpg";
		URL url = new URL(caminho);
		BufferedInputStream stream = new BufferedInputStream(url.openStream(), BUFFER_SIZE);
		BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(nomeArquivo));
		byte buf[] = new byte[BUFFER_SIZE];
		int numBytesRead;
		do {
			numBytesRead = stream.read(buf);
			if (numBytesRead > 0) {
				fos.write(buf, 0, numBytesRead);
			}
		} while (numBytesRead > 0);
		fos.flush();
		fos.close();
		stream.close();
		buf = null;

	} catch (MalformedURLException e1) {
		e1.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	
}
