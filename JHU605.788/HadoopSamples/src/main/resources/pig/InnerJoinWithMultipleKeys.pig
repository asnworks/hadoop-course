--InnerJoinWithMultipleKeys.pig
posts = LOAD 'examples_input/postAndLikes/user-posts.txt' USING PigStorage(',') AS (user:chararray,post:chararray,date:long);
likes = LOAD 'examples_input/postAndLikes/user-likes.txt' USING PigStorage(',') AS (user:chararray,likes:int,date:long);
userInfo = JOIN posts BY (user,date), likes BY (user,date);
DUMP userInfo;