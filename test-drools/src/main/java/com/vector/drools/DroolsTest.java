package com.vector.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class DroolsTest {

    public static final void main(String[] args) {
        try {
            StatefulKnowledgeSession ksession = getSessionDrools("Sample.drl");

            System.out.println("-------------- INICIO EJECUCIÓN --------------");
            System.out.println("");

            Otro otro = new Otro(1, "uno");
            ksession.insert(otro);   
            
            Otro otro2 = new Otro(2, "dos");
            ksession.insert(otro2);
            
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            ksession.insert(message);
            
            Message message2 = new Message();
            message2.setMessage("Digo adios");
            message2.setStatus(Message.GOODBYE);
            ksession.insert(message2); 
            
            Otro otro3 = new Otro();
            otro3.setDesc("tres");
            ksession.insert(otro3); 
            
            ksession.fireAllRules();
            
            System.out.println("");
            System.out.println("-------------- EJECUCIÓN TERMINADA --------------");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private static StatefulKnowledgeSession getSessionDrools(String ficheroReglas) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource(ficheroReglas), ResourceType.DRL);
        KnowledgeBuilderErrors errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return  kbase.newStatefulKnowledgeSession();
    }

}
