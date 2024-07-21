package inventorymanagementconsumer;

import com.inventorymanagementPublisher.Dao;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private static ServiceReference serviceReference;
	
	static BundleContext getContext() {
		return context;
	}
	
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Inventory Management Subscriber Starting");
		serviceReference = bundleContext.getServiceReference(Dao.class.getName());
		Dao itemTable = (Dao) bundleContext.getService(serviceReference);
		Menu menu = new Menu(itemTable);
		menu.displayMenu();
		this.stop(context);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		System.out.println("Inventory Management Subscriber Stopping");
		bundleContext.ungetService(serviceReference);
	}
	
	

}
