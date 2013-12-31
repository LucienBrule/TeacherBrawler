#_TeacherBrawler_

A 2d Fighting game pitting teachers against eachother

## Project Setup

_Import the top TeacherBrawler folder into your eclipse workspace_ 

###_Dependencies:_
-Android Adk
### To view run the program as standalone:
-pending
## Testing

_pending_

## Deploying

### _How to setup the deployment environment_

- _Dependencies: Android Adk, Libgdx, and TweenEngine._
- Download The Android Adk form within eclipse_
- Download libgdx & associated development tools form their website
-

### _How to deploy Code_
- Import the top most TeacherBrawler folder into your eclipse workspace
-Two Solvable Errors will pop up:
--Android Error: Right click your project in the explorer->preferences->Android->Build with Android 4.2.2
--<War> Error: Right click the error->quick fix->merge <war>
_To Build and run on a desktop, open the main in TeacherBrawler-desktop and use the run button within eclipse.

###_How to deploy ArtWork_
-Store all new ArtWork in the AssetesStoreFolder, located at the root of the git repository
-New platform specific artwork can be added to the game by navigating to TeacherBrawler-<platform> -> bin -> img
-Note that at this point in time, only the Desktop Build is being developed.



## Troubleshooting & Useful Tools

For Creating 9 patch Buttons(Located within the Android ADK):
-https://developer.android.com/tools/help/draw9patch.html
-Note that this does not have to be done, and Simply Adding the new button art to AssetStore will be sufficent.
-(The button will work, it will not scale with the application)

For Creating Combined Texture packs:
-http://code.google.com/p/libgdx-texturepacker-gui/

For Error Checking menu skin JSon:
-http://jsonlint.com/

For Writing a new BitMapFont:
http://www.angelcode.com/products/bmfont/
-We are using Latin, Arrows,and Mathmatical symbols

## Contributing changes

- _Internal git workflow:_
--Write code until something is accomplished, push to Master.
--Create Graphics, add to AssetStore,push to master, (wait?) for graphics to be added to game,add/revise graphics to/in internal img folder
- _Pull request guidelines_
--Have FUN! Anyone can, and is encouraged to pull this project and check it out.
- _Contact group_
--@LucienBrule on Skype
- _irc channel_
--pending growth
- _"Please open github issues"_

## License
-_GNU for now_

    Teacher Brawler, a small 2D fighting game involving teachers
    Copyright (C) <2013>  Lucien Brulé & Contributers
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

