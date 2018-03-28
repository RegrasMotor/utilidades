package com.prosegur.guvnor.negocio.utils;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;
import org.springframework.xml.xpath.NodeMapper;
import org.springframework.xml.xpath.XPathOperations;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

import com.prosegur.guvnor.negocio.service.QueryXPath;
import com.prosegur.guvnor.negocio.service.UrlRest;

public class RestUtil {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	XPathOperations xpathOperations;	

	private String usuario;
	private String password;
	private String url;

	public String getUrlCompleta(UrlRest urlRest, String...urlParams) {
		return url + urlRest.getUrl(urlParams);
	}

	public <T> ResponseEntity<T> restCallGETGuvnor(UrlRest urlRest, String...urlParams) {
		return restCallGETGuvnor(null, urlRest, urlParams);
	}
	
	public <T> ResponseEntity<T> restCallGETGuvnor(String body, UrlRest urlRest, String...urlParams) {
		return restCallGuvnor(body, urlRest, HttpMethod.GET, urlParams);
	}	
	
	public <T> ResponseEntity<T> restCallPUTGuvnor(UrlRest urlRest, String...urlParams) {
		return restCallPUTGuvnor(null, urlRest, urlParams);
	}	
	
	public <T> ResponseEntity<T> restCallPUTGuvnor(String body, UrlRest urlRest, String...urlParams) {
		return restCallGuvnor(body, urlRest, HttpMethod.PUT, urlParams);
	}	

	@SuppressWarnings("unchecked")
	public <T> ResponseEntity<T> restCallGuvnor(String body, UrlRest urlRest, HttpMethod metodo, String...urlParams) {
		return (ResponseEntity<T>) restTemplate.exchange(getUrlCompleta(urlRest, urlParams), metodo, getRequest(urlRest.getAccept(), body), urlRest.getClase());
	}
	
	public List<String> executeXPath(ResponseEntity<DOMSource> response, QueryXPath query, String...queryParams) {
		return executeXPath(response.getBody(), query, queryParams);
	}
	
	public List<String> executeXPath(ResponseEntity<DOMSource> response, QueryXPath query, NodeMapper<String> nodeMapper, String...queryParams) {
		return executeXPath(response.getBody(), query, nodeMapper, queryParams);
	}	
	
	private List<String> executeXPath(DOMSource source, final QueryXPath query, String...queryParams) {
		return executeXPath(source, query, null, queryParams);
	}
	
	private List<String> executeXPath(DOMSource source, final QueryXPath query, final NodeMapper<String> nodeMapper, String...queryParams) {
		final AtomicBoolean exclusion = new AtomicBoolean(false);
		List<String> lista = xpathOperations.evaluate(query.getQuery(queryParams), source, new NodeMapper<String>() {
	        public String mapNode(Node node, int i) throws DOMException {
	        	String valor;
	        	if (nodeMapper == null) {
	        		valor = node.getNodeValue();
	        	} else {
	        		valor = nodeMapper.mapNode(node, i);
	        	}
	        	if (query.getExlusiones().contains(valor)) {
	        		exclusion.set(true);
	        		valor = null;
	        	}
	        	return valor;
	        }
		});	
		
		List<String> listaSinExclusiones;
		if (exclusion.get()) {
			listaSinExclusiones = new ArrayList<>();
			for(String valor : lista) {
				if (valor != null) {
					listaSinExclusiones.add(valor);
				}
			}
		} else {
			listaSinExclusiones = lista;
		}

		return listaSinExclusiones;
	}
	
	public String getXmlFromSource(Source source) {
		String xml;
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult xmlOutput = new StreamResult(new StringWriter());
			transformer.transform(source, xmlOutput);
			xml = xmlOutput.getWriter().toString();
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
			xml = "";
		}
		return xml;
	}

	private HttpEntity<String> getRequest(MediaType accept, String body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept((Arrays.asList(accept)));
		setAuthBasicGuvnor(headers);
		return body == null ? new HttpEntity<String>(headers) : new HttpEntity<String>(body, headers);
	}

	private void setAuthBasicGuvnor(HttpHeaders headers) {
		String authGuvnor = usuario + ":" + password;
		final byte[] encodedAuth = Base64.encode(authGuvnor.getBytes(Charset.forName("US-ASCII")));
		final String authHeader = "Basic " + new String(encodedAuth);
		headers.add("Authorization", authHeader);
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
}
