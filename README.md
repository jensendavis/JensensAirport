# JensensAirport

Design Patterns:  
- Observer - Used when multiple objects need to be notified about the change of another object. 
This could be used when a flight is delayed or showing up to the gate. The airport would be the subject
and the Passenger and the Airplane could be the observers.
- State - Used when managing state transitions when the airport has to change states.
Could be used when a Plane has different states of boarding, flying, or landing.
- Factory Method - Used when creating different types of objects. Will make it easier
to add new types of planes or passengers when needed.