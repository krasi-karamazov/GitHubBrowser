# GitHubBrowser

Simple Github repos browser. It allows the user to type in a user name (android for example) and see the first 10 repositories. The limit is 10 so we can avoid the GutHub query limit.

When a repo is loaded the application downloads its first commit and adds it in the item view in the RecyclerView.
The application is built using the Clean architecture, MVVM for the Presentation layer. Also here I'm using Hilt as an experiment.
