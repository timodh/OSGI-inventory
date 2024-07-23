Distributed architecture project using the OSGI framework.

This is a simple inventory management system using an event bus architecture, enabling a horizantally scalable frontend and backend.

 - Can view single items or the entire inventory.
 - Can create new items.
 - Can update or delete existing items.

The frontend consumer, obtains information about the inventory after subscribing to a publisher. The UI of the application is contained entirely within the frontend.

The backend publisher, stores the inventory and communicates with subscribed consumers.
