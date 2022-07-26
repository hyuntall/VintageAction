import React, {useEffect, useState} from 'react';
import axios from 'axios';
import Auth from '../routes/Auth';

function App() {
   const [hello, setHello] = useState('')
    useEffect(() => {
        axios.get('/api/members/new')
        .then(response => setHello(response.data))
        .catch(error => console.log(error))
    }, []);
    
    return (
        <div>
            백엔드에서 가져온 데이터입니다. : {hello}
            <Auth/>
        </div>
    );
}

export default App;