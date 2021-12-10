import React from "react";
import "./accounts.scss";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Routes, Route, Link } from "react-router-dom";
import axios from "axios";

import profileImage from "../../login.svg";
import { useState } from "react";

export class Accounts extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            profiles: []
        }
        var token = localStorage.getItem('access_token');
        
        if (!token) window.location.href = '/';

        this.baseUrl = "http://localhost:8080";
        axios.defaults.headers.common['Authorization'] = "Bearer: " + token;
        axios.get(`${this.baseUrl}/profile/profile/all`)
            .then(response => {
                if (response.data.length === 0)
                    this.setState({ profiles: [{key: 0, "image":"abc", "email" : "avx", "bio" : "abc"}] })
                else
                    this.setState({ profiles: response.data })
                console.log(this.state.profiles);
            })
            .catch(function (error) {
                // handle error
                console.log(error);
            })
            .then(function () {
                // always executed
            });

    }


    render() {
        function logout(e) {
            localStorage.removeItem('access_token');
            window.location.href = '/';
        }
        return(
        <div>
            <button onClick={logout} className="logoutBtn">Log out</button>
            <Profiles profiles={this.state.profiles}/>
        </div>
        )

    }
}

function Profiles(props){
    const input = props.profiles;
    const [toggleState, setToggleState] = useState(1);

    const toggleTab = (index) => {
        setToggleState(index);
    };
    const profiles = input.map((profile) =>
        <div className="profList" key={profile.accountId}>{<Profile profile={profile} id={1} />}</div>
    );
    return(
        <div className="container2">
            <div className="bloc-tabs">
                <button className={toggleState === 1 ? "tabs active-tabs" : "tabs"} onClick={() => toggleTab(1)}> People </button>
                <button className={toggleState === 2 ? "tabs active-tabs" : "tabs"} onClick={() => toggleTab(2)}> Groups </button>
                <button className={toggleState === 3 ? "tabs active-tabs" : "tabs"} onClick={() => toggleTab(3)}> Ideas </button>
            </div>

            <div className="content-tabs">
                <div className={toggleState === 1 ? "content  active-content" : "content"}>
                    <div>
                        <form action="/" method="get">
                            <label htmlFor="header-search">
                                <span className="visually-hidden">Search for people..</span>
                            </label>
                            <input className="textInput" type="text" id="header-search" placeholder="Search for people.." name="s" />
                            <button type="submit" className="searchBtn">Search</button>
                        </form>
                    </div>
                    <h2>Profiles</h2>
                    <hr />
                        {profiles}
                    </div>
                </div>

                <div className={toggleState === 2 ? "content  active-content" : "content"}>
                    <h2>Content 2</h2>
                    <hr />
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente
                        voluptatum qui adipisci.
                    </p>
                </div>
                <div className={toggleState === 3 ? "content  active-content" : "content"}>
                <h2>Content 3</h2>
                <hr />
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos sed
                    nostrum rerum laudantium totam unde adipisci incidunt modi alias!
                    Accusamus in quia odit aspernatur provident et ad vel distinctio
                    recusandae totam quidem repudiandae omnis veritatis nostrum
                    laboriosam architecto optio rem, dignissimos voluptatum beatae
                    aperiam voluptatem atque. Beatae rerum dolores sunt.
                </p>
            </div>
        </div>
    );

}

function Profile(props) {

    if (props.profile.image == null)
        props.profile.image = "/profile_default.png";

    
    return (
            <div className="d-flex flex-row">
                <img src="/profile_default.png" className="profileImage" alt="" />
                <div className="p-2 "> <p>{props.profile.firstName}</p> </div>
                <div className="p-2 "> <p></p> </div>
                <div className="p-2 "><p></p> </div>
                <Link className="p-2 " to={"/profile/"+props.id}>View profile</Link>
            </div>
                /*<div className=" image d-flex flex-column justify-content-center align-items-center"> <button className="btn btn-secondary"><img alt="" src={props.profile.image} height="100" width="100" /></button> <span className="name mt-3">{props.profile.email}</span> <span className="idd">@instagram</span>
                    <div className="d-flex flex-row justify-content-center align-items-center gap-2"> <span className="idd1">Oxc4c16a645_b21a</span> <span><i className="fa fa-copy"></i></span> </div>
                    <div className="d-flex flex-row justify-content-center align-items-center mt-3"> <span className="number">1069 <span className="follow">Followers</span></span> </div>
                    <div className=" d-flex mt-2"> <button className="btn1 btn-dark">View Profile</button> </div>
                    <div className="text mt-3">
                    <span>{props.profile.bio}</span>
                    </div>
                    <div className="gap-3 mt-3 icons d-flex flex-row justify-content-center align-items-center"> <span><i className="fa fa-twitter"></i></span> <span><i className="fa fa-facebook-f"></i></span> <span><i className="fa fa-instagram"></i></span> <span><i className="fa fa-linkedin"></i></span> </div>
                    <div className=" px-2 rounded mt-4 date "> <span className="join">abc</span> </div>
                </div>*/

    );
  }