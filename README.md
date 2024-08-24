Application Details:-
-----------------------------------------
1)Language used:- Kotlin
2)Architecture:- MVVM Architecture(Model-View-ViewModel)
3)Repository Pattern
4)Retrofit for API call
5)Multithreading:- Coroutines
6)RoomDb for local database
7)Recyclerview for show list
8)Viewbonding for less biolerplate code to access view element
9)StateFlow for UI update
10)Glide to load images.

LocalDatabase/DogImageLibrary---->Repository---->ViewModel----->Activity(View)

Androiry Library:- 
-----------------------------------------
Retrofit for API call
API----Retrofit---->Repository(DogImageRepository)------>DogImageLibrary

Library methods:-

1) getImage():- get random image throught calling getDogImage() in library repository
2) getImages(n:Int):- get n images through calling getImageds(n) om library repository
3) getNextImages():- get next images through calling getNextImage() in library repository
4) getPreviousImage():- get previous image through calling getPreviousimage() in library repository

Application working:-
------------------------------------------


When the app is launched, the first image will be loaded through a call to the dogImageLibrary and then saved in the local database using RoomDB.
Previous button will be disable in firstimage.
When we click the Next button, if the internet is not connected, a toast message for internet connection will appear. Otherwise, the next random image will be loaded, if it hasn't been fetched earlier
Even after moving to the next image, when you return to the first image, the Previous button will be disabled.
After fetching the image, the image URL will be saved in the local database and will be retrieved from the local database thereafter

In the Main Activity, there is an input field to enter the number of images you want, between 1 and 10.
After entering the number and clicking the Submit button, the next activity will open, displaying a RecyclerView that shows multiple images as a list.
If the internet is not connected when clicking Submit, you will get an internet error toast. Additionally, if the input is not in the range of 1-10, a toast will prompt you to enter a valid input.


