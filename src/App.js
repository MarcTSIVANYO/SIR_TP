import React, { Component } from 'react';
import './App.css';
import Header from './components/Header';
import Footer from './components/Footer';
import PokemonDetail from './components/PokemonDetail';
import GroupePokemon from './components/GroupePokemon';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Switch, Route,
  Redirect } from 'react-router-dom';
  import JsonData from './data/data.json';
class App extends Component {
  state = {
    landingPageData: {},
  }
  getlandingPageData() {
    this.setState({landingPageData : JsonData})
  }
  componentDidMount() {
    this.getlandingPageData();
  }

  render() {
    return (
      <Router>
      <div>
       <Header/>
        <Switch>   
        <Route exact path="/">
          <Redirect to="/pokemon" />
      </Route>
          <Route path='/pokemon'>
            <GroupePokemon data={this.state.landingPageData.HeaderGroup} />
          </Route>
          <Route path='/pokemon-detail'>
            <PokemonDetail data={this.state.landingPageData.HeaderDetail} />
          </Route>  
        </Switch>
      </div>
    <Footer/>
    </Router>
    );
  }
}

export default App;