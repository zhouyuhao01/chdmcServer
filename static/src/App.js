import React from 'react';
import './App.css';
import request from 'request';

class App extends  React.Component{

    componentDidMount() {
        let url = "http://localhost:8080/hello";
        request(url, (error, response, body) => {
            console.log('error:', error); // Print the error if one occurred
            console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
            console.log('body:', body); // Print the HTML for the Google homepage.
        })
    }

    render()  {
    return <div>
        test
    </div>
  };
}

export default App;
