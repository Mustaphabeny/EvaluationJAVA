import React, { Component } from 'react';
import './App.css';
import HomePage from './component/HomePage'
import HeaderBar from './component/HeaderBar';
class App extends Component {
  render() {
    return (
      <div className="App">
      <HeaderBar/>
       <HomePage/>
      </div>
    );
  }
}

export default App;
