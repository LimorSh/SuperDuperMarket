# SuperDuperMarket
smart super market web system.

## General Story
### Locations
- The system will support two-dimension map: every store and customer (order in some cases) can be position between the range of 1 to 50 [1:50].
- The distance between two coordinates is calculated using Pythagorean theorem: D = SQRT( (|X1-X2|)^2 + (|Y1-Y2|)^2).
### Items
- The system will allow to define different items which sells in different stores.
- Each item has the following information:
1) Barcode (ID, unique number, integer)
2) Name (in English, a string)
3) Purchase Category (by quantity in units or by weight in KG).
### Stores
- The system will allow to define a store in which all or just part of items will be sell and set their price (per unit or 1 KG depends on its purchase category).
- Each store will locate in different coordinate (cannot have two stores in the same location).
- Each store will set the delivery cost: the store will set the price per kilometer (PPK), the delivery cost is the multiplication of the distance from the store to the order destination by the distance
- Each store can offer discounts on certain items: if customer buys specific item in a defined quantity it can have the mentioned items in the quantity mentioned in addition to add price per unit.
### Customers
- Each customer has the following information:
1) ID (unique number, integer)
2) Name (in English, a string)
- customer can order choosing items they want in the quantity they choose. As a part of an order the customer will set its location on the map for calculation the delivery cost of the items from the store/s in the order.
- part of setting the order the customer will choose if he wants a items from one store and to choose it (static order) or best cart choosing the optimal cart: lowest price of the each item in all the stores in the system. After finishing the order, the customer can buy offers in special discount. The final amount is the sum of  items price, the offers price and delivery cost.
- customers can feedback the stores he ordered from. In the feedback he will rate the store and can add additional text.

## Part 2:
desktop application UI version of the system:
- The items and stores will be defined in an XML in advance.
- No support of feedbacks.
- Desktop application UI using JavaFX architecture

### The supported actions:
1) Reading system data (stores, items and customers) from XML file and checking validity. 
2) Show information of stores, items and customers
3) Show dynamic map of the stores and customers location
4) Order items (one store or best cart) and buy offers from special discounts
5) Order history
6) Update items and prices in stores
7) Exit
* bonus:
- Animation in new order 
- Add new store dynamically
- Add new item dynamically
