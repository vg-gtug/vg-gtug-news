from google.appengine.api import users
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app
from google.appengine.ext import db
from google.appengine.ext.webapp import template
from google.appengine.api import mail

# Todo defines the data model for the Todos
# as it extends db.model the content of the class will automatically stored
class TodoModel(db.Model):
  author        = db.UserProperty(required=True)
  shortDescription = db.StringProperty(required=True)
  longDescription  = db.StringProperty(multiline=True)
  url             = db.StringProperty()
  created          = db.DateTimeProperty(auto_now_add=True)
  updated        = db.DateTimeProperty(auto_now=True)
  dueDate          = db.StringProperty(required=True)
  finished         = db.BooleanProperty()


# The main page where the user can login and logout
# MainPage is a subclass of webapp.RequestHandler and overwrites the get method
class MainPage(webapp.RequestHandler):
    def get(self):
        self.response.out.write(template.render('index.html', None))

# Register the URL with the responsible classes
application = webapp.WSGIApplication([('/', MainPage)],debug=True)

# Register the wsgi application to run
def main():
  run_wsgi_app(application)

if __name__ == "__main__":
  main()