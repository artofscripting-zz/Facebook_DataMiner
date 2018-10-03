# Import login
import login
import time
# Import fb
from fb import FB_Scraper
from threading import Thread


def get_friends(current):

    # navigate to their friends
    print 'Fetching ' + current + "'s friends..."
    
    friends = scraper.get_friends_of(current)

    print friends

    # prevents going a level deeper (friends of friends of friends)
    #if crawl:                    # if first time crawling it's the starting user
    map(q.put, friends)        # add their friends to the queue
    #    crawl = False              # don't do this again

    all_friends[current] = friends
 

#crawl = True
person = raw_input('Enter Facebook id: ')
#person = "artofscripting"
# login
cookies = login.login_firefox()

scraper = FB_Scraper(cookies)

all_friends = {}

from Queue import Queue
q = Queue()
get_friends(person)


# while there are still friends to crawl
while not q.empty():
    # load the next friend to get friends of
    current_1 = q.get()

    t = Thread(target=get_friends, args=(current_1,))

    time.sleep(5)
    t.start()
    


import json
# write the results to results.json
out = open('results.json', 'w')
out.write(json.dumps(all_friends))
out.close()

