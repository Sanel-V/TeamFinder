import React from "react";
import "./accounts.scss";
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";

export class Accounts extends React.Component {

    constructor(props) {
        super(props);

        this.baseUrl = "http://localhost:8080";

        this.profiles = [{
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
        }];
    }

    render() {
        return(<div><h1>All accounts</h1><Profiles profiles={this.profiles}/></div>) 
           
    }
}

function Profiles(props){
    const input = props.profiles;
    const profiles = input.map((profile) =>
        <div class="card p-4">{<Profile profile={profile} />}</div>
    );
    console.log(profiles);
    return(<div class="container d-flex">{profiles}</div>);
}

function Profile(props) {
    console.log(props);
    return (
            
                <div class=" image d-flex flex-column justify-content-center align-items-center"> <button class="btn btn-secondary"> <img src={props.profile.image} height="100" width="100" /></button> <span class="name mt-3">{props.profile.name}</span> <span class="idd">@instagram</span>
                    <div class="d-flex flex-row justify-content-center align-items-center gap-2"> <span class="idd1">Oxc4c16a645_b21a</span> <span><i class="fa fa-copy"></i></span> </div>
                    <div class="d-flex flex-row justify-content-center align-items-center mt-3"> <span class="number">1069 <span class="follow">Followers</span></span> </div>
                    <div class=" d-flex mt-2"> <button class="btn1 btn-dark">View Profile</button> </div>
                    <div class="text mt-3"> 
                    <span>{props.profile.bio}</span>
                    </div>
                    <div class="gap-3 mt-3 icons d-flex flex-row justify-content-center align-items-center"> <span><i class="fa fa-twitter"></i></span> <span><i class="fa fa-facebook-f"></i></span> <span><i class="fa fa-instagram"></i></span> <span><i class="fa fa-linkedin"></i></span> </div>
                    <div class=" px-2 rounded mt-4 date "> <span class="join">abc</span> </div>
                </div>
            
    );
  }
