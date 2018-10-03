# Facebook Authenticated data automation
##### Based on selenium-friends-scraper-master
#### The Java application is original and BSD.


# The java code is to help transform the "json" files into SQL, Flare for D3 and 3D visualization and NoSQL use.

### Usage
The current version is written using Selenium's Firefox web driver, which means you need Firefox installed. 
Each user will have a file with a JSON Array that contains self and self relationship and others. 
##### You will be prompted for Username and password
##### You will be prompted for a "seed" username;
The Seed username is to start the "Friends of Friends" process. The data perspective will start from that "seed"
There is no "depth" so it runs as long as you let it. 

#
# Any use of this application will clearly use provided credentials and by using this application you agree not use it for impersonation. 

### Installation
You will need geckodriver.exe in the same folder
https://github.com/mozilla/geckodriver/releases

be sure to USE PYTHON 2.7 

```bash
$ git clone https://github.com/artofscripting/Facebook_DataMiner.git
$ cd Facebook_DataMiner
$ pip install selenium
$ python main.py 
```
depending on your OS and python installation method you may see missing dependencies.
```bash
$ pip instal miss-dependency
```

# Speed Throttle
In fb.py is a line
```python
 time.sleep(5)
 ```
 This is intended to be used as a throttle. It is multi threaded but those threads are not managed. 

### NOTES
While other "cookies" are downloaded during the exploration process the only authentication and cookies used are the users "Session Cookies" 

 After stability testing, 2 copies where run of as ".exe" generated from the "main.py" python script this, after testing, for around 24 hours. Both copies ran on 1 device and used about 80% processor, by nature of the testing this will increase in ram.  This resulted in the discovery of 11,000+ files with 2.4 million unique names and 5.8 million relationships. 

