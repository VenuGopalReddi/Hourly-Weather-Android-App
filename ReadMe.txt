

The implementation:

Module I:

1.The activity initially displays two Edit Texts to enable users to give inputs of city name and country name or state name along with a search button.

2. In order to search for the weather conditions of a specific location, user must provide the details of the city and country as Input. The user then needs to ‘search’ after entering the details. It is automatically directed to the page with City weather activity.

3. Creating the SQLite database with table Cities with columns: city name, country,temperature, favorite. 

4. The activity also includes a list of previously saved cities in SQLite database.Each item must display the City name, Country name, Temperature when saved,updated date and a star button. The star button will act as the defining sign for the favorites. Initially the start should be grey, when the user taps the grey star it should be mark the stored city as a favorite and should change the star to gold. Use “star_gray.png” and “star_gold.png” from the Resources.

5. When user press a gray star which is beside of saved city then it marks the City as favorite and change the favorite field in database as TRUE then change the color of the star button toGolden. Finally,  favorite cities will be at  upwards at the top of the RecyclerView

6. If there are no stored cities in the database then the TextView displays,“There are no cities to display”.

7. The list of saved cities are displayed using RecyclerView .By using LinearLayoutManager to display the list as a vertical scrolling list. Long press on any of the items in the RecyclerView should delete it from both the list and database.

8. There will be one Menu button in the Main Activity. The menu item is “Settings.”Clicking on the Settings menu item should start a PreferenceActivity . In the preference activity the Preference for temperature units should be designed. User can change the temperature from Celsius to Fahrenheit or vice versa.
The preferences are maintained in the shared preferences and use them in the rest of the activities.

9.A Toast message  confirms that the “Temperature Unit has been changed to ºC
to ºF” or vice versa.




Module II:

Clicking on ‘Search’ button from the main activity should start with CityWeather Activity. This activity should display the detailed Weather Forecast for the searched City. The requirements are as follows

1. When Search button is pressed a progress dialogue showingparsing progress for retrieving whether report is displayed.

2. An API call should be triggered using the provided user input. The API call should return the data for consecutive 5days starting from the current day. It will return the data for every 3 hours intervals (8 weather forecasts / day roughly) per day.

3.The API call example can be: http://api.openweathermap.org/data/2.5/forecast?
q=Dallas,US&mode=xml&appid={API_Key}. Changing the mode to mode=json for JSON.

4. Parsing the data using any JSON or XML parser. Use Picasso library to load the
images.

5. At the top of the layout, displaying the text indicating the city and country as “Daily
Forecast for {City},{Country}” 

Module III:

1. Displaying a horizontal RecyclerView of items, each including the summary of Weather on that particular day. The RecyclerView  shows 3 items on screen at once, and it is scrollable horizontally to display the remaining items
 a) Each item should be clickable. Clicking on any item should show the detailed weather for that day in another RecyclerView below this view

 b) Each item in the RecyclerView should display the Date, corresponding weather icon and the average temperature of the day.

 c) We will load the weather icon image from: http://openweathermap.org/img/w/
{Symbol_serial_number}.png. In JSON, you will get the symbol serial number in the weather array, an attribute called “icon” contains it. In XML, you can find it in “var” attribute of “symbol” tag.

 d) Picking the icon of the median forecast for each day. For instance 8 weather conditions are displayed for a day of which the 5th is considered as the Median.

 e) The average temperature is also displayed. This calculates by calculating the temperatures displayed for a particular time period.

2) Below the RecyclerView, display a TextView containing “Three Hourly Forecast on {Date}”. By default, the Date will be the first date in the upper RecyclerView. If the user press on any of the rest days other than current day, the date will be changed to that day.

3)The second RecyclerView  displays three hourly forecast for the selected day.This RecyclerView should contain several items containing Time, Weather icon,Temperature, Condition, Pressure, Humidity and Wind speed and direction. This RecyclerView should be designed to display one item at once on the screen. This view should be horizontally scrollable.

4)There are two menu buttons in this activity.
   a) Save City: Pressing on this button should save the city’s details (cityname, country, temperature, favorite) into SQLite database
       i) If the city has been previously saved, then simply update the stored temperature to reflect the new temperature. A Toast should display the message, “City Updated”.
       ii) If the city has not been previously saved, then save the new city and set the favorite flag to false. In case of temperature, always use celsius unit to save into database. A Toast should display the message, “City Saved”.
       iii) Upon returning to the Main Activity the list should show the newly added city
as a saved city.
    b) Settings: Pressing on this button will do exactly same tasks with Preference Activity as Main Activity to change the units from ºC to ºF or vice versa . Temperature unit in the main activity or CityWeather Activity should be displayed based on the shared preference saved using the Preference Activity.


API Call: The API call format URL will be: api.openweathermap.org/data/2.5/forecast? q={city name},{country code}. For example, the call for Dallas, US will be
api.openweathermap.org/data/2.5/forecast?q=Dallas,US . For San Francisco it will
be: api.openweathermap.org/data/2.5/forecast?q=San_Francisco,US








