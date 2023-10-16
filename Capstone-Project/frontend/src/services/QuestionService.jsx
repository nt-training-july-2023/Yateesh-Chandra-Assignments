import axios from "axios";

const QUESTION_URL = "http://localhost:8082/question";

class QuestionService{

    getQuestions(){
        return axios.get(QUESTION_URL);
    }

    getQuestionsByQuizId(quizId){
        return axios.get(QUESTION_URL + '/byQuiz/' + quizId);
    }

    getQuestionById(questionId){
        return axios.get(QUESTION_URL + '/' + questionId);
    }

    addQuestion(Question){
        return axios.post(QUESTION_URL, Question);
    }

    deleteQuestion(questionId){
        return axios.delete(QUESTION_URL + '/' + questionId );
    }

    updateQuestion(questionId, body){
        return axios.put(QUESTION_URL + '/' + questionId, body);
    }

}

export default new QuestionService();