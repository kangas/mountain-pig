(ns mtnpig.test.daylife.NormalizeApiArgs
  (:use [mtnpig.daylife.NormalizeApiArgs] :reload)
  (:use [clojure.test]
	[clojure.pprint])
  (:import [org.apache.pig.data TupleFactory])
  )

;; in REPL:
;; (use '[mtnpig.UPPER] :reload)
;; (import [org.apache.pig.data DefaultTuple])

;; DOESN'T WORK??
(defn make-tuple-BROKEN
  [& args]
  (let [t (.newTuple (TupleFactory/getInstance))]
    (for [arg args] (.append t arg))
    t))

(defn make-tuple
  [x y]
  (let [t (.newTuple (TupleFactory/getInstance))]
    (.append t x)
    (.append t y)
    t))

;; (defn print-any
;;   [& args]
;;   (for [arg args] (prn arg)))

(def testdata
     ["accesskey=0796484176f26f4ffb0544048321f6da&signature=8a5e2db3d46ba4912a3dffb4d5d8218a&start_time=2009-09-09&end_time=2010-09-09&topic_id=03uL07q3b41KN&limit=1&source_filter_id=0dqBdTe2tx3ZI&require_speaker=&speaker_topic_id=&sort=date&offset=3"
      "source_filter_id=03khgYB5ZZ9wy&image_id=0g1K05i8by8tn&accesskey=459f2931e4d2e4710f09fbea20c203ae&_uuid=358cef4c-bc55-11df-bc38-001aa033203e&signature=9ae230cb6b90041cac23187199421c2d"
      "limit=2&accesskey=459f2931e4d2e4710f09fbea20c203ae&_uuid=341d409e-bc55-11df-bb27-001aa0332035&article_id=06H2h184sx71K&signature=c4f95de6b66d7dad0827d3d9f124968d"
      "accesskey=0796484176f26f4ffb0544048321f6da&signature=41e80bf6d0f1818d3bdecba33f6d720c&start_time=2009-09-09&end_time=2010-09-09&topic_id=00Xj5XYg2UfIb&limit=1&source_filter_id=0dqBdTe2tx3ZI&require_speaker=&speaker_topic_id=&sort=date&offset=3"
      "sort=date&require_speaker=0&accesskey=459f2931e4d2e4710f09fbea20c203ae&_uuid=34d29548-bc55-11df-8e6b-001aa00b020e&signature=cd0885bf3767d69acf61a44f7bc9ce92&limit=4&offset=0&article_id=00fncu71AV0Xk&article_id=02LdahH1271pB&article_id=02fiakW6kN1gm&article_id=03kU1B4cY89zW&article_id=04y04zr7wUdhG&article_id=07Rkc516Gb5KS&article_id=08Hm0n0eihb7e&article_id=08Xe5GQ9PWfNB&article_id=0bVd3BF4d75YV&article_id=0cmo8suanq1tK&article_id=0eCu3Nyf5Eagv&article_id=0eG3glh2xy035&article_id=0eJm7l7cSU4Mn&article_id=0fAkfhwaVh5AZ&article_id=0g26aKBcaP0oC&article_id=0g5LaUngBb2Gh&article_id=0gFFgRjf9c6Dq&article_id=0gxj8GZeak0Am&include_image=1"
      "accesskey=eaff098bbaf7edd69938de622450ae8e&signature=11ddfa938fad24f3e303ee205db53891&query=%22Falsified%22&source_whitelist=Sky+Showbiz&source_whitelist=Sky+News&source_whitelist=Sky+Sports&limit=50&sort=relevance&include_scores=1&include_image=1&start_time=1276290000&end_time=1284066000"
      "accesskey=eaff098bbaf7edd69938de622450ae8e&signature=ef2717920ec852a51de9a57628c02773&query=%22Padraig+Harrington%22&limit=1&require_speaker=1&sort=daterank&start_time=1276290000&end_time=1284066000&source_filter_id=06OTfoWgDtbON"
      "start_time=1277301300&_uuid=35698480-bc55-11df-8689-0022193502cb&topic_whitelist=04Cd7644m91T4%2C09Po4kO2EtdEz&signature=dd4e305d59692ffb2657110281d54419&limit=21&end_time=1282485300&offset=0&query=immigration+AND+hutto+AND+female+AND+detention+AND+detainees&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      ])

;; These are search queries
(def testdata-search
     ["start_time=1276292700&_uuid=955052cc-bc5d-11df-bf4d-001aa00b020e&signature=7b09c5221f1a764fae2d8ab278c4a926&source_whitelist=103000000000000359&source_whitelist=103000000000004117&limit=7&end_time=1284068700&offset=0&query=Afghanistan&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      "sort=daterank&protocol=xmlrest&start_time=1276379997&source_whitelist=0cjvg1fgwu784&source_whitelist=09tV2ZKfnV1sz&source_whitelist=0asxexDbmj0as&source_whitelist=06aS1nV85rf91&source_whitelist=0bD9eCggAXdMe&source_whitelist=03Yibap6Lr9uu&source_whitelist=06DO4LM8G77qe&limit=4&end_time=1284069597&signature=7324806a1f4b74086feb2af85eab7865&query=%28cocaine%20OR%20finding%20OR%20bologna%29%5E30%20%20OR%20cocaine%20OR%20police%20OR%20rico%20OR%20puerto%20OR%20holyoke&accesskey=8cbeb3026f0547d346035f80e400dacb&include_image=1"
      "start_time=1276292700&_uuid=95d4e230-bc5d-11df-a6a9-001aa00a5b6a&signature=90064adc2a8e8f43a2158b1b9a222f8b&source_whitelist=103000000000000008&source_whitelist=103000000000000359&source_whitelist=103000000000004117&limit=7&end_time=1284068700&offset=0&query=Microsoft+Office&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      "query=%22AMD%22%20OR%20%22ATI%22%20OR%20%22brand%22%20OR%20%22business%22&signature=620bc765c90a4cf743b41045449731a6&accesskey=df436839d70077759ad29491d80f1b91&limit=10&source_whitelist=0b0Y1dI2qogln&include_image=1&request_id=search_getRelatedArticles&_uuid=96257592-bc5d-11df-a458-001aa015aed3"
      "sort=date&source_filter_id=03khgYB5ZZ9wy&start_time=1276292700&_uuid=95e39afa-bc5d-11df-b29f-0022193502cb&signature=da8ecc95cb694da957e11a6f9c900414&limit=4&end_time=1284068700&offset=0&query=seimone&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      "sort=date&source_filter_id=09aZazF0jM7ap&show_count=1&start_time=1252532700&_uuid=96ed0f62-bc5d-11df-b600-0022193502cb&signature=a82bc3d80134f074b0c39333f1e409d9&limit=5&end_time=1284068700&offset=0&query=Rs+1&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      "start_time=1276292700&_uuid=96bae3fc-bc5d-11df-b6b0-001aa00a5b6a&signature=fc49d7130410d9e1df3b0aea9c10234e&source_whitelist=103000000000000008&source_whitelist=103000000000000359&limit=7&end_time=1284068700&offset=0&query=Cristiano+Ronaldo&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      "start_time=1249411251&_uuid=966e9934-bc5d-11df-9fbb-0022193502cb&topic_whitelist=&signature=ffb3741b7cd1be325a0c94ba96488566&limit=21&end_time=1254595251&offset=0&query=racing+AND+september+AND+sail&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      "sort=date&source_filter_id=03khgYB5ZZ9wy&show_count=1&start_time=1252532700&_uuid=9707a37c-bc5d-11df-9ecd-00188b7e2504&signature=38ee06cac638c1eabdb3bb234b809391&limit=21&end_time=1284068700&offset=0&query=obama&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      "sort=date&source_filter_id=0c8DepP5IdfQb&start_time=1276292700&_uuid=9704bf04-bc5d-11df-b5be-00188b7e2504&signature=4878c3fe7e63c382fa810a39c91a53eb&limit=11&end_time=1284068700&offset=0&query=education+OR+educate+OR+teacher+OR+school+OR+classroom+OR+college+OR+university&accesskey=459f2931e4d2e4710f09fbea20c203ae"
      ])

(deftest test-maketuple
  (is (= (make-tuple "Spam" "Eggs")
	 (doto (.newTuple (TupleFactory/getInstance))
	   (.append "Spam")
	   (.append "Eggs")))))

(deftest test-normalize-query
  (do 
    (println (normalize-query (first testdata-search)))
    (println "--")
    (pprint (for [q testdata-search] (normalize-query q)))
    ))
