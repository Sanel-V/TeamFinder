import React from "react";
import "./accounts.scss";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";

export class Accounts extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            profiles: []
        }

        this.baseUrl = "http://localhost:8080";

        axios.get(`http://localhost:8080/allprofiles`)
            .then(response => {
                this.setState({ profiles: response.data})
            })
            .catch(function (error) {
                console.log(error);
            })
            .then(function () {
                
            })



        /*this.profiles = [{
            name: 'Abc Abc',
            bio: 'I hope you enjoy learning React!',
            image: 'https://placekitten.com/g/301/301'
        },{
            name: 'Abc Abc2',
            bio: 'I hope you enjoy learning React!',
            image: 'https://placekitten.com/64/64'
        },{
            name: 'Abc Abc3',
            bio: 'I hope you enjoy learning React!',
            image: 'https://placekitten.com/g/64/64'
        }];*/
    }

    render() {
        return(<div><h1>All accounts</h1><Profiles profiles={this.state.profiles}/></div>) 
           
    }
}

function Profiles(props){
    const input = props.profiles;
    const profiles = input.map((profile) =>
        {
            return <div className="card p-4" key={profile.accountId}>{<Profile profile={profile} />}</div>
        }
    );
    return(<div className="container d-flex">{profiles}</div>);
}

function Profile(props) {
    return (
            
                <div className=" image d-flex flex-column justify-content-center align-items-center"> <button className="btn btn-secondary"> <img src={props.profile.image} height="100" width="100" /></button> <span className="name mt-3">{props.profile.firstname}</span> <span className="name">{props.profile.lastname}</span> <span className="idd">@instagram</span>
                    <div className="d-flex flex-row justify-content-center align-items-center gap-2"> <span className="idd1">Oxc4c16a645_b21a</span> <span><i className="fa fa-copy"></i></span> </div>
                    <div className="d-flex flex-row justify-content-center align-items-center mt-3"> <span className="number">1069 <span className="follow">Followers</span></span> </div>
                    <div className=" d-flex mt-2"> <button className="btn1 btn-dark">View Profile</button> </div>
                    <div className="text mt-3"> 
                    <span>{props.profile.bio}</span>
                    </div>
                    <div className="gap-3 mt-3 icons d-flex flex-row justify-content-center align-items-center"> <span><i className="fa fa-twitter"></i></span> <span><i className="fa fa-facebook-f"></i></span> <span><i className="fa fa-instagram"></i></span> <span><i className="fa fa-linkedin"></i></span> </div>
                    <div className=" px-2 rounded mt-4 date "> <span className="join">abc</span> </div>
                </div>
            
    );
  }
