import React from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import './App.css';
import Weather from './weather/weather';
import GithubRepoDemo from './github'
import InfinityListDemo from './infinity-list';
import ShoppingList from './shopping-list';
import Home from './home';
// import CorsDemo from './corsapi';
import CarApp from './car-app';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {temp: 0, desc: '', icon: '', loading: true};
  }

  handleSubmit = (event) => {
    event.preventDefault();
  }

  render() {
    return (
      <div className="App">
        <BrowserRouter>
          <div>
            <Link to="/">Home</Link>{' | '}
            <Link to="/shopping-list">Shopping List</Link>{' | '}
            <Link to="/open-weather">Weather</Link>{' | '}
            <Link to="/github-search-list">Github Search List</Link>{' | '}
            <Link to="/infitiy-scroll-list">Infinity Scroll List</Link>{' | '}
            <Link to="/cors-api">CorsDemo</Link>{' | '}
            <Link to="/car-app">Car App</Link>{' | '}
            <Switch>
              <Route exact path="/" component={Home} />
              <Route exact path="/shopping-list" component={ShoppingList} />
              <Route exact path="/open-weather" component={Weather} />
              <Route exact path="/github-search-list" component={GithubRepoDemo} />
              <Route exact path="/infitiy-scroll-list" component={InfinityListDemo} />
              <Route exact path="/car-app" component={CarApp} />
            </Switch>
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
