# Facebook data mine
#Based on selenium-friends-scraper-master
#The Java application is origial and BSD.


There is code to help transform the "json" files into SQL, Flare for D3 and 3D visualization and NoSQL use.

### Usage
The current version is written using Selenium's Firefox web driver, which means you need Firefox installed. 

Each user will have a file with a JSON Array that contains self and self relationship and others. 


### Installation
You will need geckodriver.exe in the same folder
https://github.com/mozilla/geckodriver/releases

be sure to USE PYTHON 2.7 
$ pip install selenium
$ python main.py 
depending on your OS and python installation method you may see missing dependecies.
$ pip instal miss-dependecy


#You will be prompted for Username and password and then a "seed" username;
#Any use of this application will clearly use provided credentials and by using this application you agree not use it for impersonation. 

While other "cookies" are downloaded during the exploration process the only athenticationa and cookies used are the users "Session Cookies" 

The Seed username is to start the "Friends of Friends" process. The data perspective will start from that "seed"
There is no "depth" so it runs as long as you let it. 

In fb.py is a line
 time.sleep(5)
 
 This is intended to be used as a throttle. It is multi threaded but those threads are not managed. 

 After stability testing I ran 2 copies of a "exe" generated from the "main.py" python script this, after testing, for around 24 hours. This resulted in the discovery of 11,000+ files with 2.4 million unique names and 5.8 million relationships. 
 




```
