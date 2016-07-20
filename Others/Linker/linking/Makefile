# http://blog.tremily.us/posts/Linking/
# Copyright (C) 2012  W. Trevor King
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

# The variables CC, CXX, and AR are implicit variables.  See
#   http://www.gnu.org/software/make/manual/html_node/Implicit-Variables.html

SOURCE = Makefile hello_world.cpp hello_world_string.cpp hello_world_string.h \
	print_hello_world.cpp print_hello_world.h
NM = nm -Pg

all: hello_world hello_world-static hello_world-dynamic

# directly link object files using $(CXX)
hello_world: hello_world.o print_hello_world.o hello_world_string.o
	$(CXX) -o hello_world hello_world.o print_hello_world.o hello_world_string.o

# static linking using $(CXX)
hello_world-static: hello_world.o libhello_world.a
	$(CXX) -static -o hello_world-static hello_world.o -L. -lhello_world

# dynamic linking using $(CXX)
hello_world-dynamic: hello_world.o libhello_world.so
	$(CXX) -o hello_world-dynamic hello_world.o -L. -lhello_world


# compile using $(CXX)
hello_world.o: hello_world.cpp print_hello_world.h
	$(CXX) -c hello_world.cpp
print_hello_world.o: print_hello_world.cpp print_hello_world.h
	$(CXX) -c print_hello_world.cpp
hello_world_string.o: hello_world_string.cpp hello_world_string.h
	$(CXX) -c hello_world_string.cpp

# to use them in shared object files, we'll want to compile them with PIC.
print_hello_world-PIC.o: print_hello_world.cpp print_hello_world.h
	$(CXX) -fPIC -c -o print_hello_world-PIC.o print_hello_world.cpp
hello_world_string-PIC.o: hello_world_string.cpp hello_world_string.h
	$(CXX) -fPIC -c -o hello_world_string-PIC.o hello_world_string.cpp


# create a static library with ar
libhello_world.a: print_hello_world.o hello_world_string.o
	ar -cvr libhello_world.a print_hello_world.o hello_world_string.o

# create a dynamic library with $(CXX)
libhello_world.so: print_hello_world-PIC.o hello_world_string-PIC.o
	$(CXX) -shared -Wl,-soname,libhello_world.so \
		-o libhello_world.so \
		print_hello_world-PIC.o hello_world_string-PIC.o


# optional stuff
# ==============

run:
	./hello_world
	./hello_world-static
	LD_LIBRARY_PATH=. ./hello_world-dynamic

libs: libhello_world.a libhello_world.so

inspect-object-files: hello_world.o print_hello_world.o hello_world_string.o
	$(NM) hello_world.o print_hello_world.o hello_world_string.o

inspect-executable: hello_world
	$(NM) hello_world

inspect-static-library: libhello_world.a
	$(NM) libhello_world.a

inspect-shared-libary: libhello_world.so
	$(NM) --dynamic libhello_world.so

list-executable-shared-libraries: hello_world
	ldd hello_world

# convert the README to HTML using reStructuredText
README.html: README
	rst2html.py README > README.html

# distribution
linking.tar.gz: $(SOURCE)
	mkdir linking
	cp -r $^ linking/
	tar -czf $@ linking/
	rm -rf linking/

# remove temporary files
clean:
	rm -f *.o *.a *.so hello_world hello_world-* README.html
