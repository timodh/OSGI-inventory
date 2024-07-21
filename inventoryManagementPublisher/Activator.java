package com.inventorymanagementPublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;

	ServiceRegistration tableServiceRegistration;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Inventory Management Publisher Starting");
		Dao itemTable = new ItemTable();
		tableServiceRegistration = bundleContext.registerService(Dao.class.getName(), itemTable, null);
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Inventory Management Publisher Stopping");
		tableServiceRegistration.unregister();
	}

}
