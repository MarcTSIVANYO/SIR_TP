import React, { Component } from 'react';
import PokemonDetailCard from './PokemonDetailCard';
import { Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
class PokemonDetail extends Component {
  constructor() {
    super();
    this.state = {
      pokemons: [],
      pokemonDetails: [],
      offset: 0,
      loadNumber: 20,
    }
    this.gotoNextValue = this.gotoNextValue.bind(this);
  }

  getNextOffset() {
    return this.state.offset + this.state.loadNumber;
  }

  gotoNextValue(event) {
    const newOffset = this.getNextOffset();
    this.setState({ offset: newOffset }, () => {
      this.getMorePokemon();
    });
  }

  componentDidMount() {
    this.getMorePokemon();
  }

  getMorePokemon() {
    let currentUrl = "https://pokeapi.co/api/v2/pokemon?offset=" + this.state.offset + "&limit=" + this.state.loadNumber;
    fetch(currentUrl)
      .then(response => response.json())
      .then(data => {
        if (data) {
          this.setState({ pokemons: data.results })

          this.state.pokemons.map(pokemon => {
            fetch(pokemon.url)
              .then(response =>
                response.json()
              )
              .then(data => {
                if (data) {
                  var temp = this.state.pokemonDetails
                  temp.push(data)
                  this.setState({ pokemonDetails: temp })
                }
              })
              .catch(console.log)
          })
        }
      })
      .catch(console.log)
  }


  gotoPrevPage() {
    this.setState({ currentPageUrl: this.state.prevPageUrl })
    this.getMorePokemon();
  }

  render() {
    const { pokemonDetails } = this.state;

    return (
      <div className="App">
        <h2 className="maggin">
          {this.props.data ? this.props.data.title : "Loading"} <small> {this.props.data ? this.props.data.detail : "Loading"}</small>
          <hr />
        </h2>
        <PokemonDetailCard pokemons={pokemonDetails} />
        <Button variant="outline-success" onClick={this.gotoNextValue} className="maggin">Chargez</Button>
        <div className="maggin-bottom"></div>
      </div>
    );
  }
}

export default PokemonDetail;