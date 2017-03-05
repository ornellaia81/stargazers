# Stargazer #
This project use Github API (GET /repos/:owner/:repo/stargazers)
to obtain all the stargazer of the user. It shows in a gridView name and
avatar of every stargazers.

## Running ##
1. Insert owner (for example "vmg") and repository (for example "redcarpet").
2. Click on start button to go to StargazerMatrix.
3. Click on back to start with another owner and repository.

## Testing ##
You can run HandleJSONTest to test the response code of http connection and 
the conversion of the stream from Github API in a string that contains all the info about 
the stargazers. 
