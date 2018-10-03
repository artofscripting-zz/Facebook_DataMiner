import requests,json
import os.path
from bs4 import BeautifulSoup

class FB_Scraper():
  def __init__(self, cookies):
    self.s = requests.Session()
    self.cookies = cookies
    r = requests.get('https://m.facebook.com/me', cookies = self.cookies)
    self.username = r.url.split('/')[-1].split('?')[0]

  def get_friends_of(self, username):
    startidx = 0
    friends = []
    done = False
    doneList = False
#    while not doneList:
    link = 'https://m.facebook.com/' + username + '?v=friends&startindex=' + str(startidx)
 
    while not done:
        if not os.path.isfile("data/" + username + '.friends.json'):
            r = requests.get(link, cookies=self.cookies)
            
            site_text = r.text
            new_friends = extract_friends(site_text)
            link = "https://m.facebook.com" + extract_more_link(site_text)
            #print link;
            
            #print len(new_friends)
            friends += new_friends
            startidx += len(new_friends)
            if link == "https://m.facebook.com":
                out2 = open("data/" + username + '.friends.json', 'w')
                out2.write(json.dumps(friends))
                out2.close()
                done = True
#            doneList = True

    return f7(filter(lambda f: f != username, friends))


def extract_friends(raw_html):
    content = BeautifulSoup(raw_html, features="html.parser").find('div', {"id": "root"})
    if content is not None:
        links = content.find_all("a")

        friends = []
       
        for l in links:
            href = l['href']
            username = href[1:].split('?')[0]
            if not ('.php' in username or '/' in username):
                friends.append(username)

    return friends
def extract_more_link(raw_html):
    href = ""
    content = BeautifulSoup(raw_html, features="html.parser").find('div', {"id": "m_more_friends"})
    if content is not None:
        links = content.find_all("a")


        for l in links:
            href = l['href']
    return href

def f7(seq):
    seen = set()
    seen_add = seen.add
    return [ x for x in seq if not (x in seen or seen_add(x))]
