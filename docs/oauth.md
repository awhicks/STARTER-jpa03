# OAuth Setup

This Spring Boot application is set up to use Google OAuth as it's authentication scheme.

Setting this up on localhost requires the first two steps below; getting this to work on Heroku requires an additional third step.

1. Obtaining a Github *client id* and *client secret*, which is
   done in [Github Developer Settings](https://github.com/settings/developers/).
2. Configuring the `.env` file with these values.
3. Copying the `.env` values to the Render app's configuration values.

Each of these three steps is explained in more detail below.

# About the `.env` and `.env.SAMPLE` files.

* The `.env` file is created by copying it from `.env.SAMPLE` and then editing it, e.g.

  ```
  cp .env.SAMPLE .env
  ```
* Recall that `.env` and `.env.SAMPLE` will not show up in regular directory listings; files starting with `.` are considered
  hidden files.  Use `ls -a`, or configure your Mac finder/Windows explorer to show hidden files.
* As explained below, put your client-id and client-secret into `.env`, NOT in `.env.SAMPLE`
* `.env` is never committed to the GitHub repo
* There is more information about `.env` vs. `.env.SAMPLE` on this page if you are interested: [docs/environment-variables](environment-variables.md).


## Step 1: Obtain a Github client id and client secret

1. Login to the GitHub at <https://github.com/>.

2. Navigate to your account's settings using the icon in the top right and `settings` towards the bottom of the list.

2. At the bottom of the list of options on the left, find `Developer Settings` and click on it.

3. On the left navigate to the OAuth Apps section and click `New OAuth App` in the top right.

   * `Application name` should be something unique such as jpa03-yourgithubid
   * `Homepage URL`
     * For locahost, enter: `http://localhost:8080`
     * For Render, enter: `https://jpa03-yourgithubid.onrender.com`
   * `Application Description` can be left blank
   * `Authoriztion callback URL`
     * For localhost, enter: `http://localhost:8080/login/oauth2/code/github`
     * For Render, enter: `https://jpa03-yourgithubid.onrender.com/login/oauth2/code/github`
   * `Enable Device Flow` should be left unchecked

## Step 2: Copy `.env.SAMPLE` to `.env` and enter values

In the frontend directory, use this command to copy `.env.SAMPLE` to `.env`.  Recall that you
may need to use `ls -a` to get the files to show up, since they are hidden files on Unix-like systems.

```
cp .env.SAMPLE .env
```

The file `.env.SAMPLE` **should not be edited;** it is intended to
be a template for creating a file called `.env` that contains
your repository secrets.

The `.env` is in the `.gitignore` because **a file containing secrets should NOT be committed to GitHub, not even in a private repo.

After copying, the file `.env` looks like this:

```
GOOGLE_CLIENT_ID=see-instructions
GOOGLE_CLIENT_SECRET=see-instructions
ADMIN_GITHUB=awhicks
```

Replace `see-instructions` with the appropriate values.

For ADMIN_GITHUB, add your own GitHub account and any teammates you are collaborating with after awhicks; you can separate multiple emails with commas, e.g.

```
ADMIN_GITHUB=awhicks,awh4kc
```

With this done, you should be all set to run on localhost.

## Troubleshooting

If you see this:

<img src="https://user-images.githubusercontent.com/1119017/149856156-575fb638-7db8-460a-a344-9069145aa242.png" alt="Redirect URI Mismatch" width="600" />


Try clicking the little arrow to open up the additional message:

<img src="https://user-images.githubusercontent.com/1119017/149856193-512acb25-2bfc-4e53-991b-f61de37f1ed6.png" alt="Request Details" width="600" />


Now, you'll see  the Redirect URI that the app is expecting.

If you go back to the [Google Developer Console](https://console.cloud.google.com/) you can see what you really entered.

For example, when I was getting this error message, it's because I put in this for my Redirect URI:

![image](https://user-images.githubusercontent.com/1119017/149856340-98acd5e4-8712-4723-a899-e3bf2f06d3fa.png)

Rookie mistake!  I literally had `my-heroku-app` instead of `demo-spring-react-example`.

Change it to the correct URI, click save.  Then go back to the URL for the home page of your app and refresh the page (you don't need to restart the Heroku backend; just refresh your browser page.)  Click login again, and you should get something like this:


<img src="https://user-images.githubusercontent.com/1119017/149856532-b1cda813-bd3f-4fd1-a79e-630e5929d7be.png" alt="Choose an Account" width="600" />


Success!

