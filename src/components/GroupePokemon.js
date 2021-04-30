import React, { useState, useEffect } from 'react';
import PokemonCard from './PokemonCard';
import Pagination from './Pagination';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

function GroupePokemon(props) {
  const [pokemon, setPokemon] = useState([])
  const [currentPageUrl, setCurrentPageUrl] = useState(["https://pokeapi.co/api/v2/pokemon"])

  const [nextPageUrl, setNextPageUrl] = useState()
  const [prevPageUrl, setPrevPageUrl] = useState()
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    setLoading(true)
    let cancel
    axios.get(currentPageUrl, {
      cancelToken: new axios.CancelToken(c => cancel = c)
    }).then(res => {
      if (res.data) {
        setLoading(false)
        setNextPageUrl(res.data.next)
        setPrevPageUrl(res.data.previous)
        setPokemon(res.data.results.map(p => p))
      }
    })

    return () => cancel()
  }, [currentPageUrl])


  if (loading) return "Loading ..."

  function gotoNextPage() {
    setCurrentPageUrl(nextPageUrl)
  }

  function gotoPrevPage() {
    setCurrentPageUrl(prevPageUrl)
  }
  return (
    <div className="App">
      <h2 className="maggin">
        {props.data ? props.data.title : "Loading"} <small> {props.data ? props.data.detail : "Loading"}</small>
        <hr />
      </h2>
      <PokemonCard
        pokemon={pokemon} />
      <Pagination
        gotoNextPage={nextPageUrl ? gotoNextPage : null}
        gotoPrevPage={prevPageUrl ? gotoPrevPage : null}
      />
      <div className="maggin-bottom"></div>
    </div>
  );
}
export default GroupePokemon;
