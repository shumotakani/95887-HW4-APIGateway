package edu.shuwang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.shuwang.form.QuoteIdForm;
import edu.shuwang.form.QuoteNameForm;
import edu.shuwang.model.Author;
import edu.shuwang.model.Quote;

@RestController
public class APIGatewayController {
    
	@Value("${service.quote.random.uri}")
    private String quoteServerRandomUri;
    @Value("${service.quote.list.uri}")
    private String quoteServerListUri;
    @Value("${service.quote.save.uri}")
    private String quoteServerSaveUri;
    @Value("${service.author.id.uri}")
    private String authorServerIdUri;
    
    @RequestMapping("/api/quote/random")
    public Quote random() {
    	RestTemplate restTemplate = new RestTemplate();
        String uriRandom = quoteServerRandomUri;
        QuoteIdForm qf = restTemplate.getForObject(uriRandom, QuoteIdForm.class);
        
        String uriAuthorId = authorServerIdUri+qf.getAuthorId();
        Author a = restTemplate.getForObject(uriAuthorId, Author.class);
        
        Quote q = new Quote(qf.getText(), qf.getSource(), a);
        return q;
    }
    
    @RequestMapping("/api/quote/list")
    public Quote[] list(String authorName) {
    	RestTemplate restTemplate = new RestTemplate();
        String uriList = quoteServerListUri+authorName;
        QuoteIdForm[] qfs = restTemplate.getForObject(uriList, QuoteIdForm[].class);
        
        Quote[] qs = new Quote[qfs.length];
        for (int i = 0; i < qfs.length; i++) {
        	String uriAuthorId = authorServerIdUri+qfs[i].getAuthorId();
        	Author a = restTemplate.getForObject(uriAuthorId, Author.class);
			qs[i] = new Quote(qfs[i].getText(), qfs[i].getSource(), a);
		}
        return qs;
    }
    
    @RequestMapping(value = "/api/quote", method = RequestMethod.POST)
    public void saveQuote(@RequestBody QuoteNameForm qnf) {
    	
    	RestTemplate restTemplate = new RestTemplate();
    	String uri = quoteServerSaveUri;
    	System.out.println(qnf.getText());
    	System.out.println(qnf.getSource());
    	System.out.println(qnf.getAuthorName());

    	ResponseEntity<Long> st = restTemplate.postForEntity(uri, qnf, Long.class);
    	System.out.println(st.getBody());
    }
    
    
    public Quote fallback() {
        Quote q = new Quote();
        Author a = new Author("Confucius");
        q.setText("The superior man is modest in his speech, but exceeds in his actions.");
        q.setAuthor(a);
        return q; 
    }
}
