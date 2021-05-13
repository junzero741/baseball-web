import firebase from "../config/firebase-config";

const socialMediaAuth = async (provider) => {
	let res;
	try {
		res = await firebase
			.auth()
			.setPersistence(firebase.auth.Auth.Persistence.SESSION)
			.then(() => {
				return firebase.auth().signInWithPopup(provider);
			});
		return res.user;
	} catch (error) {
		return error;
	}
};

export default socialMediaAuth;
