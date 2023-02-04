import React, { useState } from 'react';
import axios from 'axios';
import { useEffect } from 'react';

const ChatSection = () => {
    const [message, setMessage] = useState('');
    const [response, setResponse] = useState([]);
    useEffect(() => {
        axios.get('/api')
            .then(res => {
              setResponse(res.data);
            })
            .catch(err => {
              console.error(err);
            });
    }, [message]);
  
    const handleSubmit = (e) => {
      e.preventDefault();
      axios.post('/api', {data : { message: message }})
        .then(res => {
          setResponse(res.data);
          console.log(response);
        })
        .catch(err => {
          console.error(err);
        });
    }
  
    let chatList;
    if (Array.isArray(response)) {
      chatList = response.map((chat, index) => {
        const parsedPrompt = JSON.parse(chat.prompt);
        return (
          <div key={index}>
            <div className="d-flex justify-content-start mb-4">
                <div className="img_cont_msg">
                    <img src="assets/user.jpeg" className="rounded-circle user_img_msg" alt='msg' />
                </div>
                <div className="msg_cotainer">
                {parsedPrompt.data.message}
                </div>
            </div>
       
                <div className="d-flex justify-content-end mb-4">
                    <div className="msg_cotainer_send">
                    {chat.response}
                    </div>
                    <div className="img_cont_msg">
                <img src="assets/chatboot.jpeg" className="rounded-circle user_img_msg" alt='res' />
                    </div>
                </div>
          </div>
        );
      });
    }
  
    return (
      <section className="container my-5" id="espaceChat">
        <div className="card-body border border-dark rounded msg_card_body" id="boite">
        {chatList}
</div>
        <div className="card-footer">
          <form onSubmit={handleSubmit}>
            <div className="input-group">
              <div className="input-group-append">
                <button type="reset" className="input-group-text attach_btn">
                  <i className="bi bi-arrow-clockwise"></i>
                </button>
              </div>
              <textarea 
                name="msg" 
                className="form-control type_msg" 
                placeholder="Type your message..."
                value={message}
                onChange={e => setMessage(e.target.value)}
              />
              <div className="input-group-append">
                <button type="submit" className="input-group-text send_btn">
                  <i className="bi bi-send"></i>
                </button>
              </div>
            </div>
          </form>
        </div>
      </section>
    );
  };
  export default ChatSection;
