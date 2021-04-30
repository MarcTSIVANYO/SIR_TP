import React from 'react'
import { Button } from 'react-bootstrap';
export default function Pagination({ gotoNextPage, gotoPrevPage }) {
    return (
      <div>
        {gotoPrevPage &&  
        <Button variant="outline-primary" onClick={gotoPrevPage} className="maggin">Précédent</Button>}
        {gotoNextPage &&<Button variant="outline-success" onClick={gotoNextPage} className="maggin">Suivant</Button>}
      </div>
    )
}