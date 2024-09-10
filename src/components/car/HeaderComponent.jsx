import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import Untitled from'../../images/Untitled.png'
import Navbar from '../design/Navbar'

class HeaderComponent extends Component {

    constructor(props){
        super(props)
            this.state={

            }
        }

    render() {
        return (
            <div style={{height:"10%"}}>
            
                <Link to={'/dashboard'}>
                
                <header>

                    <img src={Untitled}></img>
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
                        
                    </nav>


                </header>
                </Link>
                <Navbar/>
            </div>
        )
    }
}
 export default HeaderComponent