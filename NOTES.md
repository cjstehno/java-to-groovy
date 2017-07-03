

==== show
- gradle
- simple enums
- Pogo (tostring, equalsHash)
- sortable
- loops
- logging
- immutable (name)
- junit to spock testing
- remove need for external libs

======= walk-through

* show/describe project (pom file, models, main, etc)
* show test and run it

* first create new `groovy-version` directory as a copy of `java-version`

* convert pom to gradle
  * show simplicity
  * show automatic test report
  * delete the pom file.

* rename the main & test `java` dirs to `groovy` (could configure too)
* change the file extension of Processor.java to .groovy
* show still builds

* remove all the ;
* remove `public`
* remove `final` inside methods :-(
* replace `println`
* build and everything ok

* - start getting rid of things

* refactor away the SimpleDateFormat (groovy extension to Date)
* show build

* let's remove the FileUtils dependency
    * replace readLines with groovy extended File
    * replace write with groovy extended File
    * show still build
    * need to convert test to finish removal
    * rename test (remove ;, public)
    * replace `readFileToString` to `text`
    * remove commons-io from build
    * show ok build

* back to processor
* convert the loader loop (to each)
    * split -> var expansion List
    * id as long
    * add using <<

* lets go look at Person
    * change extension, remove ; and public
    * replace getter/setters & ctor
    * remove equals/hashcode/tostring
    * convert date formatter as before
    * replace builder with gstring
    * build ok
    * better yet - replace toJson with groovy json
    * build ok
    * replace comparable
    * build ok (review start -> finish)
    
* lets look at Name
    * rename file and remove public, ;
    * note that this is immutable object - use immutable rather than canonical
    * tojason as teh other
    * sortable as other
    * now we can remove commons-lang
    * build ok

* lets look at the enum Gender
    * change extension and remove ; and public
    * make tupleconstructor to remove ctor/getter
    * gstring in exception 
    * note tostring override
    * reduce for loop to find
    * build ok
    
* go back to processor
    * convert the other for loop to each
    * chained name access as groovy props
    * groovy truth and ==
    * gstring in file name
    * build ok
    
* at this point production code converted (fewer lines, less code to worry about)

* lets do more with the test (spock)
    * replace junit with `testCompile 'org.spockframework:spock-core:1.0-groovy-2.4'`
    * note it still builds since it includes junit
    * extend specification
    * replace test with similar spec (remove asserts too)
    * quick into to spock

* update the test a bit
* update the asserts in the test (and fix strings)


compare line counts