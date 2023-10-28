# t2530-programming-training
Repository dedicated to Team 2530 members programming training.

### How to use

1. Sign up for [GitHub](https://github.com/signup)

Go through the signup process. 

2. (Optional) Join the Teams organization

Our team has an official Github organization located at https://github.com/Team2530/. This is where robot code, website code, team documentation and more is stored.

To join it, ask a Coach or mentor to send you an invitation.

3. Create a repository

Create a 'clone' of this repository template by clicking on the green "Use this template" button in github. Then, in the dropdown click on the "Create a new repository" option.

![Dropdown menu](assets/create-new-repo-1.png)

Fill out the name and description fields, and then click on the green "Create repository" button.
The suggested name for the repository is `t2530-programming-training`.

![Create Repo Dialog](assets/create-new-repo-2.png)

After this step is complete, you should have a repository that looks something like this.

![Created repository](assets/create-new-repo-3.png)

4. Install all needed programs for Java programming

There are a lot of tools available for programming with Java. Here is a list of what you will need to install:

**Universal** - *These applications should work on most all machines*

| Name | Details | Installation |
| ---- | ------- | ------------ |
| Git | Git is a Version Control System (VCS). It is used to track changes in a project (called the repository), and allows for collaboration between programmers on the same project. Think of it as a more powerful version of the edit history in Google Docs. | **Windows**: Installing Git on windows is a little bit more difficult than other platforms. Download Git Bash from https://gitforwindows.org/, and it should be installed properly. Open Git Bash and run `git --version` to make sure it is installed and working properly. <br/>**Linux**: On Linux, Git might come pre-installed with your distribution. Try running `git --version` to see if it is installed. For Ubuntu/Debian based distros, you can install Git via `sudo apt install git`. <br/>**MacOS**: To install Git on MacOS, just type `git` into your terminal. It should ask if you want to install git. |
| IDE | Your IDE (integrated development environment) is where all code is written. Our team uses [Visual Studio Code](https://code.visualstudio.com/) (commonly shortened to VS Code) and sometimes [VSCodium](https://vscodium.com/), which is a "community-driven, freely-licensed binary distribution" of VS Code. | **VSCode**: To download the regular version of VSCode, go to https://code.visualstudio.com/ and download and run the installer on the homepage. <br/> **VSCodium**: Go to the releases page at https://github.com/VSCodium/vscodium/releases and download the binary for your platform. |
| Language Support for Java(TM) by Red Hat | This is a plugin for VSCode that adds language support for the Java programming language. By default, VSCode only offers minimal support for Java, such as syntax highlighting. This plugin adds the Go to definition feature, complex autocompletion, linting, support for build systems and more. | In VSCode, go to the Extensions menu by clicking the icon with four squares. <br/>![Extensions menu](assets/java-extension-1.png)<br/> Find Language Support for Java(TM) by Red Hat, and click the blue install button.  |
| WPILib | WPILib is the "the standard software library provided for teams to write code for their FRC® robots". | See https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html. |

**Windows only** - *These applications only exist for Windows. They are not needed to use this repository, but are required to deploy robot code.*
| Name | Details | Installation |
| ---- | ------- | ------------ |
| FRC Game Tools | FRC Game tools contains the drivers station application (used to control the robot), and some tools for imaging the RoboRIO. | See https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/frc-game-tools.html |

5. Clone your repository

The command to clone a repository is `git clone https://github.com/[your-username]/[repo-name]`. For example, to clone the original t2530-programming-training repository, you would use this command: `git clone https://github.com/michael-m-2983/t2530-programming-training`. Your command should have your username instead of mine.

A better method of cloning a repository is using the command `git clone https://[your-username]:[access-token]@github.com/[your-username]/[repo-name]`.
To do this, you will need to generate a [Personal Access Token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-personal-access-token-classic) with at minimum the repo scope.

The benifit of including your username and access token is that when pushing or pulling from the github repository, you aren't required to enter the token in every time.

*Don't include the square brackets when cloning the repository.*

6. Make changes to the source code

<!-- TODO: In a different markdown file, explain how to do some of these examples. -->

Change things in the code.

Some ideas (for changes in the breakout game) if you can't think of anything:
* Change the size of the ball
* Make paddle movement smoother
* Increase speed of the ball
* Make more bricks appear when you destroy all of them

To test your changes, first `cd` into the directory of the project you are working on (e.g. `cd breakout`), then run `./run.sh` (or `.\run.bat` if you are on windows).


7. Stage and commit your changes

When you are happy with the changes that you have made, run `git status -u`.

Here are the changes that I made:

![Changes that I made](assets/stage-and-commit-changes-1.png)

Run `git add .` to stage all of those changes. Staging a change means putting it on the list of changes made to the code that will be sent to the server when you push to GitHub.

<!-- TODO: A section on setting user.name and user.email -->

Finally, to commit the changes you made to your local copy of the repository, run `git commit`. This should open a dialog of some kind that asks you for a commit message. By default on most linux distros, nano is used. To use nano to write a commit message, simply type the commit message, then press Control and X, press the Y key on your keyboard, and then press enter twice. 

8. Push your changes back to your repository

You have commited your changes to your local copy of the repository. Now, it is finally time to send your changes to the GitHub repository.
To do this, just run `git push`. 

If when cloning the repository onto your local machine you used the format which includes your access token, everything should work fine. If you used the shorter form, you now will have to generate a [Personal Access Token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-personal-access-token-classic) and enter it and your github username into the command line.

### Resources
* [W3Schools Java Tutorial](https://w3schools.com/java)
* [Temurin Java 11 JDK Releases](https://adoptium.net/temurin/releases/?package=jdk&version=11&arch=x64) - *You will have to change the architecture if you are not on a x64 machine*.
* [Creating a GitHub Personal Access Token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-personal-access-token-classic)