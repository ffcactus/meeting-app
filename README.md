# meeting-app
A demo for a pre interview.

Here are some tips for you to understanding the implementation.
* The test hope the code to be production ready, so it's not just a pure algorithm.
  I try to write the code so that it can be quite easily changed to a real application. 
* The implementation assumes the booking should be persistent, the application should be stateless. 
  So it has a **MeetingRepository** interface. 
  It assumes the **MeetingRepository** can be easily implemented by a SQL style database.
* It has a **sdk** package. It contains some generalized class. For example, a booking string is represented by a **Booking**.
  a **MeetingService** provide APIs for stateless booking operations.
* It assumes that a booking can not across the midnight. So all the meetings are grouping by date. It can also reduce the calculation time and data loading time.
* In the example, the application seems to be processing a bulk of requests at once, and the booking requests are not in their submit time,
  However, The **MeetingService** assume the request should come one by one. The sort to the requests happens in the unit test. 
  