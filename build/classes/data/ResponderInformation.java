package data;

import java.util.Vector;

/**
 * Responder information interface to be used by planner.
 */
public interface ResponderInformation {
    
    String getName();
    Vector<Capability> capabilities();
	Vector<String> getContacts();

}