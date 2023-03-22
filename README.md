# STARTER-jpa03

Storybook is here:
* Production: <https://awhicks.github.io/STARTER-jpa03-docs/>
* QA:  <https://awhicks.github.io/STARTER-jpa03-docs-qa/>

The GitHub actions script to deploy the Storybook to QA requires some configuration; see [docs/github-actions.md](docs/github-actions.md) for details.

If these repos are not yet setup, see the setup steps in [`docs/storybook.md`](docs/storybook.md).

# Setup before running application

Before running the application for the first time,
you need to do the steps documented in [`docs/oauth.md`](docs/oauth.md).

Otherwise, when you try to login for the first time, you
will likely see an error such as:

<img src="https://user-images.githubusercontent.com/1119017/149858436-c9baa238-a4f7-4c52-b995-0ed8bee97487.png" alt="Authorization Error; Error 401: invalid_client; The OAuth client was not found." width="400"/>

# Getting Started on localhost

* Open *two separate terminal windows*
* In the first window, start up the backend with:
  ```
  mvn spring-boot:run
  ```
* In the second window:
  ```
  cd frontend
  npm install  # only on first run or when dependencies change
  npm start
  ```

Then, the app should be available on <http://localhost:8080>

If it doesn't work at first, e.g. you have a blank page on  <http://localhost:8080>, give it a minute and a few page refreshes.  Sometimes it takes a moment for everything to settle in.

If you see the following on localhost, make sure that you also have the frontend code running in a separate window.

```
Failed to connect to the frontend server... On Render, be sure that PRODUCTION is defined.  On localhost, open a second terminal window, cd into frontend and type: npm install; npm start";
```

### Security Note
#### The `.env` file  should *not* be committed to GitHub

I already made this point, but I really, really want to emphasize it.

One of the values in the `.env` file is called a client *secret* for a reason.

If it leaks, it can be used for nefarious purposes to compromise the security of your account; so don't let it leak!

Never, ever, commit those to GitHub!

The same is true with Personal Access Tokens from GitHub (only more so!)

Security starts with making smart choices about how to handle credentials and tokens. The stakes get higher when you start being trusted with credentials and tokens at an employer, so learning how to handle these with care now is a part of developing good developer habits.

The staff reserve the right to deduct points if we find that you have committed your `.env` file to GitHub.

## About OAuth

OAuth is a protocol that allows you to delegate the login/logout
functionality (user authentication) to a third party such as
Google, Facebook, GitHub, Twitter, etc.  If you've ever used
a website that allows you to "Login with Google" or "Login With Facebook", then chances are good that app was built using OAuth.

## Also set up the GitHub Actions secrets

In the file `docs/github-actions.md` there are instructions for configuring
GitHub Actions so that it runs the tests for both the JavaScript and Java code in the repo.

You should follow these instructions to get the CI/CD pipeline set up so that
you have a green check, and not a red X, on the main branch of your repo.

## Reminder about running your web app on `localhost`

To get this demo app running on `localhost`, we need to do the following:

* Do the OAuth Configuration steps linked to from the README to get a client-id and client-secret
  and put them in your `.env` file
* Add your own github username after `awhicks` separated by commas (no spaces) like this

  ```
  ADMIN_GITHUB=awhicks,yourteamsgithubids
  ```

* Then, use `mvn compile` to make sure that the code compiles.
* Next, try `mvn test` to be sure that the test cases pass.
* Then, run the following two commands separately in their own shells
  * In the first, launch the React frontend using `npm start` in the frontend directory. This should start the frontend on port 3000
  * In the second, launch the Spring Boot backend using `mvn spring-boot:run`.  This should start a web server on port 8080

The `mvn spring-boot:run` command is a shortcut that is provided for us to be able to run the jar file.  It does pretty much the same thing as
if we ran the `.jar` file and specified the class containing our `main` on the command line.

When the app is up and running, try logging in with your Github Account.

# SQL Database access

On localhost:
* The SQL database is an H2 database and the data is stored in a file under `target`
* Each time you do `mvn clean` the database is completely rebuilt from scratch
* You can access the database console via a special route, <http://localhost:8080/h2-console>
* For more info, see [docs/h2-database.md](/docs/h2-database.md)

# Getting Started on Render

## Create an Account on Render

Visit <https://render.com> and click Sign Up

I encourage you to use either the GitHub or Google buttons to create your account, rather than signing up with a new Username/Password, but it's your choice

You'll also need to follow the OAuth set up instructions here: [`docs/oauth.md`](docs/oauth.md).

## Create a new Render.com application and deploy your code

Follow the instructions at <https://ucsb-cs156.github.io/topics/render/deploying_any_repo_to_render.html> to create a new app. Use the name `jpa03-yourgithubid`

This should result in an app at the address `https://jpa03-yourgithubid.onrender.com`

## Define Environment Variables

On Render, you'll need to set the following configuration variable:

* Navigate to Env Groups on the navbar
* Create a `New Environment Group`
* Name your group `jpa03-yourgithubid`
* Add the following configuration variables and submit

| Environment Variable | Value | Notes |
|-|-|
| ADMIN_GITHUB | awhicks,your-github-id | `awhicks,yourgithubid` |
| GITHUB_CLIENT_ID | your-github-client-id | Obtain value from Github Developer Settings; you can also fill in `temp` as a temporary placeholder |
| GITHUB_CLIENT_SECRET | your-github-client-SECRET | Obtain value from Github Developer Settings; you can also fill in `temp` as a temporary placeholder |
| PORT | 8080 | Maps port 8080 to the https service that is exposed to the outside world |
| PRODUCTION | true | Signals that we want to build both frontend and backend |


## Try Logging into your App

The URL for your app should be something like <https://jpa03-yourgithubid.onrender.com>.

Try logging in, and logging out.

## What if it doesn't work?

This is a new technology to the course staff, so bear with us as we work out some of the issues

If you have trouble, please post to piazza with your issue
* Please include a brief description of what problem you are running into
* Please include copy/paste of error messages, and/or screenshots if applicable
* Please include a link to your jpa03 repo
* Please include a link to your Render.com deployment.
* Please add the staff emails to your Render.com deployment
  - To add staff, use the `Sharing` menu option in Render.com and add each staff by their email

# Accessing swagger

To access the swagger API endpoints, use:

* <http://localhost:8080/swagger-ui/index.html>


# To run React Storybook

* cd into frontend
* use: npm run storybook
* This should put the storybook on http://localhost:6006
* Additional stories are added under frontend/src/stories

* For documentation on React Storybook, see: https://storybook.js.org/

# Set up Storybook repos

Storybook is a utility that produces documentation sites for React Apps.

We are going to set up two separate repos that have the same name as your repo, but with the suffixes `-docs` and `-docs-qa`.

For example, if your GitHub id is `awhicks`, you'll have a main repo:

* <tt>Spring-Setup-awhicks</tt>

In this step, you'll create two additional repos:

* <tt>Spring-Setup-awhicks-docs</tt>
* <tt>Spring-Setup-awhicks-docs-qa</tt>

There is a script that you can try to run that should create these for you; the script is in the form of a "GitHub Action".

If the script works, great!

If not, you can create these two repos manually.

Instructions are under the `docs` directory in the file `storybook.md`.  Follow those instructions.

One step that isn't in the instructions:
* After you create the `-docs` and `-docs-qa` repos, you will need to click to create a `README.md` in order to
  establish the `main` branch, before you run the Github Actions scripts to populate the repos.

When you are finished, update the links in the README.md file so that they point to your storybook repos:

Before:

```
* Production: <https://awhicks.github.io/jpa03_solution-docs/storybook>
* QA:  <https://awhicks.github.io/jpa03_solution-docs-qa/storybook>
```

After (with your URLs)

```
* Production: <https://CS-3704-S23.github.io/jpa03-yourgithubid-docs/>
* QA:  <https://CS-3704-S23.github.io/jpa03-yourgithubid-docs-qa/>
```

Make sure that the links work.

# Submitting your work for grading

When your group has a running web app, find the assignment Spring Setup on Canvas and make a submission.

* There is no autograder for this assignment on Gradescope!
* This assignment will be manually graded
* Please make it easy for the staff by making clickable URLs in your Canvas submission.

In the text area, enter something like this, substituting your repo name and your Render app name (just select one for grading):

<div style="font-family:monospace;">
repo name: https://github.com/CS-3704-S23/Spring-Setup-chrislee123<br><br>
on Render.com: https://jpa03-chrislee123.onrender.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

# Grading Rubric:

* (20 pts) Basic Logistics
  - Having a repo with the correct name in the correct organization with the starter code for this lab
  - The links on Canvas are clickable links (to make it easier to test your app)
  - Information of how you are communicating within your group (slack, discord, groupme, etc)
  - README has a link to your repo.
* (20 pts) Having a running web app at <tt>https://CS-3704-S23-githubusername.onrender.com</tt>
* (20 pts) Running web app has the ability to login with OAuth through a Github Account.
* (20 pts) Storybooks for `docs` and `docs-qa` are both set up properly.
* (20 pts) GitHub Actions runs correctly and there is a green check (not a red X) on your main branch
