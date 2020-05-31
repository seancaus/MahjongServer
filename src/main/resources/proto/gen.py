#coding=utf8

import os
import re
import os.path
import platform
import subprocess as sub

wdir    = './'
cmd     = 'protoc --proto_path=./ --java_out=./ *.proto -I ./'

sub.call(cmd, shell = True, cwd=wdir)