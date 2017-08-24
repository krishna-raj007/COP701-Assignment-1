from mininet.cli import CLI
from mininet.term import makeTerm
import random
import string
import time

def startall( self, line ):
    "mycmd is an example command to extend the Mininet CLI"
    net = self.mn
    for h in net.hosts:
        account = ''.join(random.choice('0123') for _ in range(8))
        print "Start java in " , h , " with account name " , account
        cmdLine = "java -jar ~/dev/A1.jar " + account
        print "  Calling command: " , cmdLine
	# Running in foreground
        net.terms += makeTerm(h, cmd=cmdLine)
        # Running in background
        # h.sendCmd(cmdLine)
        time.sleep(0.5)
CLI.do_startall = startall